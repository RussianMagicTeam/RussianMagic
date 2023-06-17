package ru.rikgela.russianmagic.objects.blockentities

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import ru.rikgela.russianmagic.network.RMMessages
import ru.rikgela.russianmagic.network.packet.ManaSyncS2CPacket
import ru.rikgela.russianmagic.objects.mana.IManaSpreader
import ru.rikgela.russianmagic.objects.mana.Mana
import ru.rikgela.russianmagic.objects.mana.transfer.ManaSpreader

abstract class AbstractRMMagicSourceBlockEntity(
    blockEntityTypeIn: BlockEntityType<*>,
    blockPos: BlockPos,
    blockState: BlockState,
    tier: Int
)
    : BlockEntity(blockEntityTypeIn, blockPos, blockState), IManaSpreader {

    protected val mana: Mana = object : Mana(tier * 100, tier * 1000) {
        override fun onContentsChanged(){
            setChanged()
            if (!level!!.isClientSide()) {
                RMMessages.sendToClients(ManaSyncS2CPacket(this, worldPosition))
            }
        }
    }
    private val manaSpreader: IManaSpreader = ManaSpreader(mana)

    //IManaReceiver implementation
    override val currentMana: Int
        get() = manaSpreader.currentMana
    override val baseMaxMana: Int
        get() = manaSpreader.baseMaxMana
    override val maxSpread: Int
        get() = manaSpreader.maxSpread

    var playerStringUuid: String? = null

    fun manaIsUsableByPlayer(player: Player): Float {
        return if (level!!.getBlockEntity(blockPos) !== this || player.stringUUID != playerStringUuid) {
            0F
        } else {
            1F / player.distanceToSqr(
                blockPos.x.toDouble() + 0.5,
                blockPos.y.toDouble() + 0.5,
                blockPos.z.toDouble() + 0.5
            ).toFloat()
        }
    }

    fun manaIsUsableByBlockEntity(player: Player): Float {
        return if (level!!.getBlockEntity(blockPos) !== this || player.stringUUID != playerStringUuid) {
            0F
        } else {
            1F / player.distanceToSqr(
                blockPos.x.toDouble() + 0.5,
                blockPos.y.toDouble() + 0.5,
                blockPos.z.toDouble() + 0.5
            ).toFloat()
        }
    }
    protected open fun markDirty() {
        if (level != null && level!!.hasChunkAt(blockPos)) {
            level!!.sendBlockUpdated(blockPos, blockState, blockState, 2)
            level!!.getChunkAt(blockPos).isUnsaved = true
        }
    }

    override fun setChanged() {
        if (level != null) {
            markDirty()
            val state = blockState
            if (state.hasAnalogOutputSignal()) level!!.updateNeighbourForOutputSignal(worldPosition, state.block)
        }
    }
    private fun update() {
        setChanged()
        if (level != null && level?.isClientSide != true) {
            level!!.sendBlockUpdated(
                blockPos, this.blockState, this.blockState, 2
            )
        }
    }

    companion object {
        fun tick(level: Level, blockPos: BlockPos, blockState: BlockState, blockEntity: AbstractRMMagicSourceBlockEntity) {
            if (!level.isClientSide) {
                if (blockEntity.currentMana < blockEntity.baseMaxMana)
                    blockEntity.mana.fill(Integer.max((blockEntity.baseMaxMana - blockEntity.currentMana) / 100, 1))
                blockEntity.update()
            }
        }
    }

    override fun load(nbt: CompoundTag) {
        super.load(nbt)
        this.playerStringUuid = nbt.getString("PlayerUUID")
        this.mana.deserializeNBT(nbt.getCompound("Mana"))
    }

    override fun saveAdditional(nbt: CompoundTag) {
        nbt.putString("PlayerUUID", playerStringUuid)
        nbt.put("Mana", mana.serializeNBT())
        super.saveAdditional(nbt)
    }


    override fun spread(points: Int, rate: Float): Int {
        val ret = manaSpreader.spread(points, rate)
        if (ret != 0) update()
        return ret
    }

}