package ru.rikgela.russianmagic.network.packet

import net.minecraft.client.Minecraft
import net.minecraft.core.BlockPos
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.item.ItemStack
import net.minecraftforge.items.ItemStackHandler
import net.minecraftforge.network.NetworkEvent
import ru.rikgela.russianmagic.objects.blockentities.AbstractRMFurnaceBlockEntity
import java.util.function.Supplier


class ItemStackSyncS2CPacket {
    var itemStackHandler: ItemStackHandler
    var blockPos: BlockPos
    constructor(itemStackHandler: ItemStackHandler, blockPos: BlockPos){
        this.itemStackHandler = itemStackHandler
        this.blockPos = blockPos
    }

    constructor(buf: FriendlyByteBuf) {
        val collection: List<ItemStack> = buf.readCollection(
            { initialCapacity: Int -> ArrayList(initialCapacity) }
        ) { obj: FriendlyByteBuf -> obj.readItem() }
        itemStackHandler = ItemStackHandler(collection.size)
        for (i in collection.indices) {
            itemStackHandler.insertItem(i, collection[i], false)
        }
        blockPos = buf.readBlockPos()
    }

    fun toBytes(buf: FriendlyByteBuf) {
        val list: MutableCollection<ItemStack> = ArrayList()
        for (i in 0 until itemStackHandler.slots) {
            list.add(itemStackHandler.getStackInSlot(i))
        }
        buf.writeCollection(list, FriendlyByteBuf::writeItem)
//        buf.writeCollection(list, FriendlyByteBuf.Writer { obj: FriendlyByteBuf -> obj.writeItem() })
        buf.writeBlockPos(blockPos)
    }

    fun handle(supplier: Supplier<NetworkEvent.Context>): Boolean {
        val context: NetworkEvent.Context  = supplier.get()
        context.enqueueWork {
            if (Minecraft.getInstance().level!!.getBlockEntity(blockPos) is AbstractRMFurnaceBlockEntity)
                (Minecraft.getInstance().level!!.getBlockEntity(blockPos) as AbstractRMFurnaceBlockEntity).setHandler(this.itemStackHandler)
        }
        return true
    }
}