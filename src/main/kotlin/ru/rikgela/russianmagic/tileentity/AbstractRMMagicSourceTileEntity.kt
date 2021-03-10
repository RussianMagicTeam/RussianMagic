package ru.rikgela.russianmagic.tileentity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SUpdateTileEntityPacket
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import net.minecraft.util.text.ITextComponent
import net.minecraft.util.text.TranslationTextComponent
import net.minecraftforge.common.util.Constants
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.mana.IMana
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.mana.Mana
import ru.rikgela.russianmagic.mana.ManaReceiver
import ru.rikgela.russianmagic.util.RMMekanism

abstract class AbstractRMMagicSourceTileEntity(tileEntityTypeIn: TileEntityType<*>, val rmMekanism: RMMekanism)
    : TileEntity(tileEntityTypeIn), ITickableTileEntity, IManaReceiver {
    var customName: ITextComponent? = null

    private val mana: IMana = Mana
            .withParams(rmMekanism.tier * 100, rmMekanism.tier * 1000)
    private val manaReceiver: IManaReceiver = ManaReceiver(mana)

    val name: ITextComponent
        get() = customName ?: defaultName

    private val defaultName: ITextComponent
        get() = TranslationTextComponent("container.$MOD_ID.${rmMekanism.name}")


    fun update() {
        markDirty()
        if (world != null && world?.isRemote != true) {
            world!!.notifyBlockUpdate(getPos(), this.blockState, this.blockState,
                    Constants.BlockFlags.BLOCK_UPDATE)
        }
    }

    private fun dropProgress() {
        world!!.setBlockState(getPos(), this.blockState)
        update()
    }

    override fun tick() {
        if (world?.isRemote == false) {

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


    fun isUsableByPlayer(player: PlayerEntity): Boolean {
        return if (world!!.getTileEntity(pos) !== this) {
            false
        } else {
            player.getDistanceSq(pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5) <= 64.0
        }
    }

    //IManaReceiver implementation
    override val currentMana: Int
        get() = manaReceiver.currentMana
    override val maxMana: Int
        get() = manaReceiver.maxMana
    override val maxTransfer: Int
        get() = manaReceiver.maxTransfer

    override fun transfer(points: Int): Int {
        val ret = manaReceiver.transfer(points)
        if (ret != points) update()
        return ret
    }
}