package ru.rikgela.russianmagic.client.particle

import com.mojang.brigadier.StringReader
import net.minecraft.network.PacketBuffer
import net.minecraft.particles.IParticleData
import net.minecraft.particles.IParticleData.IDeserializer
import net.minecraft.particles.ParticleType
import ru.rikgela.russianmagic.init.RMParticles


class ManaParticleData : IParticleData {
    override fun getType(): ParticleType<*> {
        return RMParticles.MANA_PARTICLE.get()
    }

    override fun write(buffer: PacketBuffer) {}
    override fun getParameters(): String {
        return ""
    }

    companion object {
        val DESERIALIZER: IDeserializer<ManaParticleData> = object : IDeserializer<ManaParticleData> {
            override fun deserialize(particleTypeIn: ParticleType<ManaParticleData>, reader: StringReader?): ManaParticleData {
                return ManaParticleData()
            }

            override fun read(particleTypeIn: ParticleType<ManaParticleData>, buffer: PacketBuffer): ManaParticleData {
                return ManaParticleData()
            }
        }
    }
}