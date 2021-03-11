package ru.rikgela.russianmagic.client.particle

import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.minecraft.network.PacketBuffer
import net.minecraft.particles.IParticleData
import net.minecraft.particles.IParticleData.IDeserializer
import net.minecraft.particles.ParticleType
import net.minecraft.util.registry.Registry


class ColoredParticleType(
    alwaysShow: Boolean,
    val red: Float = 0f,
    val green: Float = 0f,
    val blue: Float = 0f,
    val hash: Int? = null
) :
    ParticleType<ColoredParticleType>(alwaysShow, DESERIALIZER), IParticleData {
    override fun getType(): ParticleType<ColoredParticleType> {
        return this
    }

    override fun write(buffer: PacketBuffer) {}
    override fun getParameters(): String {
        return Registry.PARTICLE_TYPE.getKey(this).toString()
    }

    companion object {
        private val DESERIALIZER: IDeserializer<ColoredParticleType> = object : IDeserializer<ColoredParticleType> {
            @Throws(CommandSyntaxException::class)
            override fun deserialize(
                particleTypeIn: ParticleType<ColoredParticleType>,
                reader: StringReader
            ): ColoredParticleType {
                return particleTypeIn as ColoredParticleType
            }

            override fun read(
                particleTypeIn: ParticleType<ColoredParticleType>,
                buffer: PacketBuffer
            ): ColoredParticleType {
                return particleTypeIn as ColoredParticleType
            }
        }
    }

    fun copyWithColor(red: Float, green: Float, blue: Float): ColoredParticleType {
        return ColoredParticleType(this.alwaysShow, red, green, blue, this.hashCode())
    }

    override fun hashCode(): Int {
        return hash ?: super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return if (other != null)
            other.hashCode() == this.hashCode()
        else
            super.equals(other)
    }
}