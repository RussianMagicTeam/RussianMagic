package ru.rikgela.russianmagic.common

import ru.rikgela.russianmagic.objects.player.mana.PLAYER_MANA_CAP
import com.google.gson.Gson
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.network.PacketBuffer
import net.minecraft.util.math.BlockPos
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.network.NetworkEvent
import net.minecraftforge.fml.network.NetworkHooks
import ru.rikgela.russianmagic.objects.mana.IMana
import ru.rikgela.russianmagic.objects.mana.IManaReceiver
import ru.rikgela.russianmagic.objects.mana.IManaSpreader
import ru.rikgela.russianmagic.objects.player.mana.IPlayerMana
import ru.rikgela.russianmagic.objects.tileentity.AbstractRMFurnaceTileEntity
import java.lang.Integer.min
import java.util.function.Supplier


class RMCCMessage(
        private val cmd: Command
) {
    companion object {
        enum class Commands {
            OPEN_GUI, TRANSFER_MANA_FROM_PLAYER_TO_TILE_ENTITY, TRANSFER_MANA_FROM_TILE_ENTITY_TO_PLAYER
        }
        data class Command(
                val cmd: Commands,
                val data: String
        )
        data class Pos(
                val x: Int,
                val y: Int,
                val z: Int
        )

        @JvmStatic
        fun fromString(msg: String): RMCCMessage {
            return RMCCMessage(Gson().fromJson(msg, Command::class.java))
        }

        @JvmStatic
        fun fromPacketBuffer(pb: PacketBuffer): RMCCMessage {
            return fromString(pb.readByteArray().decodeToString())
        }

        @OnlyIn(Dist.CLIENT)
        fun send(msg: RMCCMessage) {
            if (Minecraft.getInstance().connection != null) {
                RMNetworkChannel.sendToServer(msg)
            }
        }

        @OnlyIn(Dist.CLIENT)
        fun openTileEntityGui(x: Int, y: Int, z: Int) {
            val pos = Pos(x, y, z)
            val cmd = Command(Commands.OPEN_GUI, Gson().toJson(pos))
            val msg = RMCCMessage(cmd)
            send(msg)
        }

        @OnlyIn(Dist.CLIENT)
        fun transferManaToTileEntity(x: Int, y: Int, z: Int) {
            val pos = Pos(x, y, z)
            val cmd = Command(Commands.TRANSFER_MANA_FROM_PLAYER_TO_TILE_ENTITY, Gson().toJson(pos))
            val msg = RMCCMessage(cmd)
            send(msg)
        }

        @OnlyIn(Dist.CLIENT)
        fun transferManaFromTileEntity(x: Int, y: Int, z: Int) {
            val pos = Pos(x, y, z)
            val cmd = Command(Commands.TRANSFER_MANA_FROM_TILE_ENTITY_TO_PLAYER, Gson().toJson(pos))
            val msg = RMCCMessage(cmd)
            send(msg)
        }
    }

    fun encoder(pb: PacketBuffer) {
        pb.writeString(Gson().toJson(cmd))
    }

    fun handle(ctx: Supplier<NetworkEvent.Context>) {
        ctx.get().enqueueWork {
            val playerEntity: ServerPlayerEntity = ctx.get().sender ?: return@enqueueWork
            val world = playerEntity.world ?: return@enqueueWork
            if (cmd.cmd == Commands.OPEN_GUI) {
                val pos = Gson().fromJson(cmd.data, Pos::class.java)
                val tile = world.getTileEntity(BlockPos(pos.x, pos.y, pos.z))
                if (tile is AbstractRMFurnaceTileEntity) {
                    NetworkHooks.openGui(playerEntity, tile as INamedContainerProvider?, BlockPos(pos.x, pos.y, pos.z))
                }
            } else if (cmd.cmd == Commands.TRANSFER_MANA_FROM_PLAYER_TO_TILE_ENTITY) {
                val pos = Gson().fromJson(cmd.data, Pos::class.java)
                val tile = world.getTileEntity(BlockPos(pos.x, pos.y, pos.z))
                if (tile is IManaReceiver) {
                    if (PLAYER_MANA_CAP != null) {
                        val playerMana: IMana =
                            playerEntity.getCapability(PLAYER_MANA_CAP!!).orElseThrow { RuntimeException("WTF???") } as IMana
                        val transferManaCount = playerMana.currentMana
                        tile.transfer(playerMana.currentMana)
                        if (transferManaCount > 0) playerMana.consume(transferManaCount)
                    }
                }
            } else if (cmd.cmd == Commands.TRANSFER_MANA_FROM_TILE_ENTITY_TO_PLAYER) {
                val pos = Gson().fromJson(cmd.data, Pos::class.java)
                val tile = world.getTileEntity(BlockPos(pos.x, pos.y, pos.z))
                if (tile is IManaSpreader) {
                    if (PLAYER_MANA_CAP != null) {
                        val playerMana: IPlayerMana = playerEntity.getCapability(PLAYER_MANA_CAP!!)
                            .orElseThrow { RuntimeException("WTF???") } as IPlayerMana
                        playerMana.fill(
                            tile.spread(
                                min(tile.maxSpread, tile.currentMana),
                                playerMana.rate
                            )
                        )
                    }
                }
            }
        }
        ctx.get().packetHandled = true
    }
}
