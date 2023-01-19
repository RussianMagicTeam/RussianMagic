package ru.rikgela.russianmagic.network.packet

import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.FriendlyByteBuf
import net.minecraftforge.network.NetworkEvent
import ru.rikgela.russianmagic.objects.blockentities.AbstractRMFurnaceBlockEntity
import ru.rikgela.russianmagic.objects.mana.Mana
import java.util.function.Supplier

class ManaSyncS2CPacket {
    var manaHandler: Mana
    var blockPos: BlockPos
    constructor(manaHandler: Mana, blockPos: BlockPos){
        this.manaHandler = manaHandler
        this.blockPos = blockPos
    }

    constructor(buf: FriendlyByteBuf) {
        val collection: CompoundTag = buf.readAnySizeNbt()!!
        manaHandler = Mana()
        manaHandler.deserializeNBT(collection)
        blockPos = buf.readBlockPos()
    }

    fun toBytes(buf: FriendlyByteBuf) {
        buf.writeNbt(manaHandler.serializeNBT())
        buf.writeBlockPos(blockPos)
    }

    fun handle(supplier: Supplier<NetworkEvent.Context>): Boolean {
        val context: NetworkEvent.Context  = supplier.get()
        context.enqueueWork {
            if (Minecraft.getInstance().level!!.getBlockEntity(blockPos) is AbstractRMFurnaceBlockEntity)
                (Minecraft.getInstance().level!!.getBlockEntity(blockPos) as AbstractRMFurnaceBlockEntity).setManaHandler(this.manaHandler)
        }
        return true
    }
}
