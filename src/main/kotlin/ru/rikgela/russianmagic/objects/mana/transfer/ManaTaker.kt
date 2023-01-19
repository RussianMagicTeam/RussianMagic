package ru.rikgela.russianmagic.objects.mana.transfer

import net.minecraft.core.BlockPos
import net.minecraft.core.Vec3i
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.level.Level
import ru.rikgela.russianmagic.objects.mana.IManaSpreader
import ru.rikgela.russianmagic.objects.mana.IManaTaker
import java.lang.Float.max
import java.lang.Float.min

class ManaTaker : IManaTaker {

    // Properties
    private var spreaderPos: BlockPos? = null
    private var levelResourceKey: String = ""
    private val baseDistance = 100F
    override val isConnectedToManaSpreader: Boolean
        get() = spreaderPos != null
    override val spreaderWorldPos: String
        get() = if (spreaderPos != null)
            levelResourceKey + ", " +
                    spreaderPos!!.x.toString() + ", " +
                    spreaderPos!!.y.toString() + ", " +
                    spreaderPos!!.z.toString() else "NULL"
    override val rate: Float
        get() = 0.0F
    // To save
    override fun serializeNBT(): CompoundTag {
        val nbt = CompoundTag()
        nbt.putString("levelResourceKey", this.levelResourceKey)
        if (this.spreaderPos != null) {
            nbt.putIntArray(
                "spreaderPos",
                intArrayOf(this.spreaderPos!!.x, this.spreaderPos!!.y, this.spreaderPos!!.z)
            )
        }
        return nbt
    }

    // To load
    override fun deserializeNBT(nbt: CompoundTag){
        this.levelResourceKey = nbt.getString("levelResourceKey")
        if (nbt.contains("spreaderPos")){
            val unpackedSpreaderPos = nbt.getIntArray("spreaderPos")
            this.spreaderPos = BlockPos(unpackedSpreaderPos[0], unpackedSpreaderPos[1], unpackedSpreaderPos[2])
        } else{
            this.spreaderPos = null
        }
    }

    // Important

    // Methods

    override fun connectToManaSpreader(
        manaSpreaderPos: BlockPos,
        levelManaSpreader: Level
    ) {
        val blockEntity = levelManaSpreader.getBlockEntity(manaSpreaderPos)
        if (blockEntity is IManaSpreader) {
            spreaderPos = manaSpreaderPos
            this.levelResourceKey = levelManaSpreader.dimension().toString()
        }

    }

    override fun disconnectToManaSpreader() {
        this.spreaderPos = null
        this.levelResourceKey = ""
    }

    fun getRate(manaConsumer: BlockPos, sensitivity: Float): Float {
        return if(spreaderPos != null) {
            val distance = kotlin.math.sqrt(
                spreaderPos!!.distSqr(
                    Vec3i(
                        manaConsumer.x,
                        manaConsumer.y,
                        manaConsumer.z
                    )
                )
            ).toFloat()
            val rate =
                sensitivity *
                        if (distance >= this.baseDistance) 1F / distance
                        else 1F - distance / this.baseDistance
            max(min(rate, 1F), 0F)
        } else {
            0F
        }
    }

    override fun getMana(
        points: Int,
        levelManaSpreader: Level,
        manaConsumer: BlockPos,
        sensitivity: Float
    ): Int {
        if (!isConnectedToManaSpreader) {
            return 0
        }
        var ret: Int? = null
        val blockEntity = levelManaSpreader.getBlockEntity(spreaderPos!!)
        if (blockEntity is IManaSpreader) {
            if (manaConsumer != BlockPos(-1, -1, -1)) {
                val rate = getRate(manaConsumer, sensitivity)
                ret = blockEntity.spread(points, rate)
            }
        } else {
            //Todo action if cannot get tileEntity
        }
        return ret ?: 0
    }
}

