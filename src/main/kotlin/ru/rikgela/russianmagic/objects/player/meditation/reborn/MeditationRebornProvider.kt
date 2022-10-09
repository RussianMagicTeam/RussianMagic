import net.minecraft.nbt.INBT
import net.minecraft.nbt.IntNBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.ICapabilitySerializable
import net.minecraftforge.common.util.LazyOptional

@CapabilityInject(IMeditationReborn::class)
var MEDITATION_REBORN_CAP: Capability<IMeditationReborn>? = null

class MeditationRebornProvider(private val instance: IMeditationReborn?) : ICapabilitySerializable<INBT> {

    override fun <T : Any?> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap == MEDITATION_REBORN_CAP) LazyOptional.of { instance as T } else LazyOptional.empty()
    }

    override fun deserializeNBT(nbt: INBT?) {
        MEDITATION_REBORN_CAP?.storage?.readNBT(MEDITATION_REBORN_CAP, this.instance, null, nbt)
    }

    override fun serializeNBT(): INBT {
        return MEDITATION_REBORN_CAP?.storage?.writeNBT(MEDITATION_REBORN_CAP, this.instance, null) ?: IntNBT.valueOf(0)
    }
}
