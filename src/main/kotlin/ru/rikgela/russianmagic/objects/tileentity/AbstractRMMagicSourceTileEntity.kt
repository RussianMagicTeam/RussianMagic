package ru.rikgela.russianmagic.objects.tileentity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SUpdateTileEntityPacket
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.text.ITextComponent
import net.minecraftforge.common.util.Constants
import ru.rikgela.russianmagic.objects.mana.IMana
import ru.rikgela.russianmagic.objects.mana.IManaSpreader
import ru.rikgela.russianmagic.objects.mana.Mana
import ru.rikgela.russianmagic.objects.mana.transfer.ManaSpreader

abstract class AbstractRMMagicSourceTileEntity(tileEntityTypeIn: TileEntityType<*>)
    : TileEntity(tileEntityTypeIn), ITickableTileEntity, IManaSpreader {
    var customName: ITextComponent? = null

    private val mana: IMana = Mana
            .withParams(50, 500)
    private val manaSpreader: IManaSpreader = ManaSpreader(mana)

    //IManaReceiver implementation
    override val currentMana: Int
        get() = manaSpreader.currentMana
    override val baseMaxMana: Int
        get() = manaSpreader.baseMaxMana
    override val maxSpread: Int
        get() = manaSpreader.maxSpread

    var playerUuid: ITextComponent? = null

    fun manaIsUsableByPlayer(player: PlayerEntity): Float {
        return if (world!!.getTileEntity(pos) !== this || player.displayNameAndUUID != playerUuid) {
            0F
        } else {
            1F / player.getDistanceSq(pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5)
                .toFloat()
        }
    }

    fun manaIsUsableByTileEntity(player: PlayerEntity): Float {
        return if (world!!.getTileEntity(pos) !== this || player.displayNameAndUUID != playerUuid) {
            0F
        } else {
            1F / player.getDistanceSq(pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5)
                .toFloat()
        }
    }

    private fun update() {
        markDirty()
        if (world != null && world?.isRemote != true) {
            world!!.notifyBlockUpdate(
                getPos(), this.blockState, this.blockState,
                Constants.BlockFlags.BLOCK_UPDATE
            )
        }
    }

    override fun tick() {
        if (world?.isRemote == false) {
            if (currentMana < baseMaxMana)
                mana.fill(Integer.max((baseMaxMana - currentMana) / 100, 1))
            update()
        }
    }

    override fun read(compound: CompoundNBT) {
        super.read(compound)
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"))
        }
        if (compound.contains("PlayerUUID", Constants.NBT.TAG_STRING)) {
            playerUuid = ITextComponent.Serializer.fromJson(compound.getString("PlayerUUID"))
        }
        mana.loadFromByteArray(compound.getByteArray("Mana"))
    }

    override fun write(compound: CompoundNBT): CompoundNBT {
        super.write(compound)
        compound.putByteArray("Mana", mana.toByteArray())
        if (playerUuid != null) {
            compound.putString("PlayerUUID", ITextComponent.Serializer.toJson(playerUuid!!))
        }
        if (customName != null) {
            compound.putString("CustomName", ITextComponent.Serializer.toJson(customName!!))
        }
        return compound
    }

    override fun getUpdatePacket(): SUpdateTileEntityPacket? {
        val nbt = CompoundNBT()
        write(nbt)
        return SUpdateTileEntityPacket(pos, 0, nbt)
    }

    override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) {
        read(pkt.nbtCompound)
    }

    override fun getUpdateTag(): CompoundNBT {
        val nbt = CompoundNBT()
        write(nbt)
        return nbt
    }

    override fun handleUpdateTag(nbt: CompoundNBT) {
        read(nbt)
    }

    override fun spread(points: Int, rate: Float): Int {
        val ret = manaSpreader.spread(points, rate)
        if (ret != 0) update()
        return ret
    }
}