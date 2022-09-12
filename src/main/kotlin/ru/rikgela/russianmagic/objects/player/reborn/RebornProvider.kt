import net.minecraft.nbt.INBT
import net.minecraft.nbt.IntNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional
import ru.rikgela.russianmagic.objects.player.reborn.IReborn

@CapabilityInject(IReborn::class)
var REBORN_CAP: Capability<IReborn>? = null

class RebornProvider(private val instance: IReborn?) : ICapabilitySerializable<INBT> {

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap == REBORN_CAP) LazyOptional.of { instance as T } else LazyOptional.empty()
    }

    override fun deserializeNBT(nbt: INBT?) {
        REBORN_CAP?.storage?.readNBT(REBORN_CAP, this.instance, null, nbt)
    }

    override fun serializeNBT(): INBT {
        return REBORN_CAP?.storage?.writeNBT(REBORN_CAP, this.instance, null) ?: IntNBT.valueOf(0)
    }
}
