package ru.rikgela.russianmagic.common

import com.google.gson.Gson
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.network.PacketBuffer
import net.minecraft.util.math.BlockPos
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.network.NetworkEvent
import net.minecraftforge.fml.network.NetworkHooks
import ru.rikgela.russianmagic.mana.IMana
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.mana.MANA_CAP
import ru.rikgela.russianmagic.tileentity.RMMarbleFurnaceTileEntity
import java.util.function.Supplier


class RMCCMessage(
        private val cmd: Command
) {
    companion object {
        enum class Commands {
            OPEN_GUI, TRANSFER_MANA_FROM_PLAYER_TO_TILE_ENTITY
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
            RMNetworkChannel.sendToServer(msg)
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
                if (tile is RMMarbleFurnaceTileEntity) {
                    NetworkHooks.openGui(playerEntity, tile as INamedContainerProvider?, BlockPos(pos.x, pos.y, pos.z))
                }
            } else if (cmd.cmd == Commands.TRANSFER_MANA_FROM_PLAYER_TO_TILE_ENTITY) {
                val pos = Gson().fromJson(cmd.data, Pos::class.java)
                val tile = world.getTileEntity(BlockPos(pos.x, pos.y, pos.z))
                if (tile is IManaReceiver) {
                    if (MANA_CAP != null) {
                        val playerMana: IMana = playerEntity.getCapability(MANA_CAP!!).orElseThrow { RuntimeException("WTF???") } as IMana
                        val transferManaCount = playerMana.currentMana - tile.transfer(playerMana.currentMana)
                        if (transferManaCount > 0) playerMana.consume(transferManaCount)
                    }
                }
            }
        }
        ctx.get().packetHandled = true
    }
}
