package ru.rikgela.russianmagic.objects.tileentity

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
import ru.rikgela.russianmagic.objects.mana.ManaSpreader

abstract class AbstractRMMagicSourceTileEntity(tileEntityTypeIn: TileEntityType<*>)
    : TileEntity(tileEntityTypeIn), ITickableTileEntity, IManaSpreader {
    var customName: ITextComponent? = null

    private val mana: IMana = Mana
            .withParams(50, 500)
    private val manaSpreader: IManaSpreader = ManaSpreader(mana)

    private fun update() {
        markDirty()
        if (world != null && world?.isRemote != true) {
            world!!.notifyBlockUpdate(getPos(), this.blockState, this.blockState,
                    Constants.BlockFlags.BLOCK_UPDATE)
        }
    }

    override fun tick() {
        if (world?.isRemote == false) {
                mana.fill(Integer.max((maxMana - currentMana) / 100, 1))
            update()
        }
    }

    override fun read(compound: CompoundNBT) {
        super.read(compound)
        if (compound.contains("CustomName", Constants.NBT.TAG_STRING)) {
            customName = ITextComponent.Serializer.fromJson(compound.getString("CustomName"))
        }
        mana.loadFromByteArray(compound.getByteArray("Mana"))
    }

    override fun write(compound: CompoundNBT): CompoundNBT {
        super.write(compound)
        compound.putByteArray("Mana", mana.toByteArray())
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

    //IManaReceiver implementation
    override val currentMana: Int
        get() = manaSpreader.currentMana
    override val maxMana: Int
        get() = manaSpreader.maxMana
    override val maxSpread: Int
        get() = manaSpreader.maxSpread

    override fun spread(points: Int): Int {
        val ret = manaSpreader.spread(points)
        if (ret != 0) update()
        return ret
    }
}