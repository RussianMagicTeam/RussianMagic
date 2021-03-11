/*import com.mojang.brigadier.StringReader
import com.mojang.brigadier.exceptions.CommandSyntaxException
import net.minecraft.network.PacketBuffer
import net.minecraft.particles.IParticleData
import net.minecraft.particles.IParticleData.IDeserializer
import net.minecraft.particles.ParticleType
import net.minecraft.util.math.MathHelper
import java.awt.Color
import java.util.*
import javax.annotation.Nonnull
import kotlin.jvm.Throws

/**
 * Created by TGG on 25/03/2020.
 *
 * The particle has two pieces of information which are used to customise it:
 *
 * 1) The colour (tint) which is used to change the hue of the particle
 * 2) The diameter of the particle
 *
 * This class is used to
 * 1) store this information, and
 * 2) transmit it between server and client (write and read methods), and
 * 3) parse it from a command string i.e. the /particle params
 */
class ManaParticleData(val tint: Color, diameter: Double) : IParticleData {

    @Nonnull
    override fun getType(): ParticleType<ManaParticleData> {
        return StartupCommon.manaParticleType
    }

    // write the particle information to a PacketBuffer, ready for transmission to a client
    override fun write(buf: PacketBuffer) {
        buf.writeInt(tint.red)
        buf.writeInt(tint.green)
        buf.writeInt(tint.blue)
        buf.writeDouble(diameter)
    }

    // used for debugging I think; prints the data in human-readable format
    @Nonnull
    override fun getParameters(): String {
        return String.format(Locale.ROOT, "%s %.2f %i %i %i",
                this.type.registryName, diameter, tint.red, tint.green, tint.blue)
    }

    /**
     * @return get diameter of particle in metres
     */
    val diameter: Double

    companion object {
        private fun constrainDiameterToValidRange(diameter: Double): Double {
            val MIN_DIAMETER = 0.05
            val MAX_DIAMETER = 1.0
            return MathHelper.clamp(diameter, MIN_DIAMETER, MAX_DIAMETER)
        }

        // The DESERIALIZER is used to construct FlameParticleData from either command line parameters or from a network packet
        val DESERIALIZER: IDeserializer<ManaParticleData> = object : IDeserializer<ManaParticleData> {
            // parse the parameters for this particle from a /particle command
            @Nonnull
            @Throws(CommandSyntaxException::class)
            override fun deserialize(@Nonnull type: ParticleType<ManaParticleData>, @Nonnull reader: StringReader): ManaParticleData {
                reader.expect(' ')
                val diameter = constrainDiameterToValidRange(reader.readDouble())
                val MIN_COLOUR = 0
                val MAX_COLOUR = 255
                reader.expect(' ')
                val red = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR)
                reader.expect(' ')
                val green = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR)
                reader.expect(' ')
                val blue = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR)
                val color = Color(red, green, blue)
                return ManaParticleData(color, diameter)
            }

            // read the particle information from a PacketBuffer after the client has received it from the server
            override fun read(@Nonnull type: ParticleType<ManaParticleData>, buf: PacketBuffer): ManaParticleData {
                // warning! never trust the data read in from a packet buffer.
                val MIN_COLOUR = 0
                val MAX_COLOUR = 255
                val red = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR)
                val green = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR)
                val blue = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR)
                val color = Color(red, green, blue)
                val diameter = constrainDiameterToValidRange(buf.readDouble())
                return ManaParticleData(color, diameter)
            }
        }
    }

    init {
        this.diameter = constrainDiameterToValidRange(diameter)
    }
}*/