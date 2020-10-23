package ru.rikgela.russianmagic.container

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.ClickType
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.Slot
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.IWorldPosCallable
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.items.SlotItemHandler
import ru.rikgela.russianmagic.init.RMBlocks
import ru.rikgela.russianmagic.init.RMContainerTypes
import ru.rikgela.russianmagic.tileentity.RMMarbleFurnaceTileEntity
import ru.rikgela.russianmagic.util.FunctionalIntReferenceHolder
import java.util.*
import java.util.function.IntConsumer
import java.util.function.IntSupplier
import javax.annotation.Nonnull
import kotlin.math.min

class RMMarbleFurnaceContainer(windowID: Int,
                               playerInv: PlayerInventory,
                               val tileEntityMarble: RMMarbleFurnaceTileEntity
) : Container(RMContainerTypes.RM_MARBLE_FURNACE_CONTAINER.get(), windowID) {
    private val canInteractWithCallable: IWorldPosCallable = IWorldPosCallable.of(tileEntityMarble.world!!, tileEntityMarble.pos)
    private var currentSmeltTime: FunctionalIntReferenceHolder? = null

    // Client Constructor
    constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?) : this(windowID, playerInv, getTileEntity(playerInv, data))

    override fun canInteractWith(playerIn: PlayerEntity): Boolean {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, RMBlocks.RM_MARBLE_FURNACE_BLOCK.get())
    }

    private fun transferToInventory(player: PlayerEntity, index: Int): ItemStack {
        val slot = inventorySlots[index]
        for (i in 0..35) {
            if (inventorySlots[i].hasStack && inventorySlots[i].stack.isItemEqual(slot.stack)) {
                val transferCount = min(slot.stack.count, inventorySlots[i].stack.maxStackSize - inventorySlots[i].stack.count)
                slot.stack.count -= transferCount
                inventorySlots[i].stack.count += transferCount
                if (slot.stack.isEmpty) return player.activeItemStack
            }
        }

        for (i in 0..35) {
            if (!inventorySlots[i].hasStack) {
                inventorySlots[i].putStack(slot.stack)
                inventorySlots[index].putStack(ItemStack.EMPTY)
                return player.activeItemStack
            }
        }

        return player.activeItemStack
    }

    @Nonnull
    override fun transferStackInSlot(player: PlayerEntity, index: Int): ItemStack {

        var returnStack = player.activeItemStack
        val slot = inventorySlots[index]

        if (slot.hasStack) {
            if (index == 36 || index == 37) {
                transferToInventory(player, index)
            } else {
                if (inventorySlots[36].hasStack && inventorySlots[36].stack.isItemEqual(slot.stack)) {
                    val transferCount = min(slot.stack.count, inventorySlots[36].stack.maxStackSize - inventorySlots[36].stack.count)
                    slot.stack.count -= transferCount
                    inventorySlots[36].stack.count += transferCount
                    if (slot.stack.isEmpty) return returnStack
                } else if (!inventorySlots[36].hasStack) {
                    inventorySlots[36].putStack(slot.stack)
                    inventorySlots[index].putStack(ItemStack.EMPTY)
                    return returnStack
                }
            }
        }
        return returnStack
    }

    @get:OnlyIn(Dist.CLIENT)
    val smeltProgressionScaled: Int
        get() = if (currentSmeltTime!!.get() != 0 && tileEntityMarble.maxSmeltTime != 0) currentSmeltTime!!.get() * 24 / tileEntityMarble.maxSmeltTime else 0

    companion object {
        private fun getTileEntity(playerInv: PlayerInventory?, data: PacketBuffer?): RMMarbleFurnaceTileEntity {
            Objects.requireNonNull(playerInv, "playerInv cannot be null")
            Objects.requireNonNull(data, "data cannot be null")
            val tileAtPos = (playerInv!!).player.world.getTileEntity((data!!).readBlockPos())
            if (tileAtPos is RMMarbleFurnaceTileEntity) {
                return tileAtPos
            }
            throw IllegalStateException("TileEntity is not correct $tileAtPos")
        }
    }

    override fun slotClick(slotId: Int, dragType: Int, clickTypeIn: ClickType, player: PlayerEntity): ItemStack {
        if(slotId == 37) {
            if (player.inventory.itemStack.isEmpty) {
                return super.slotClick(slotId, dragType, clickTypeIn, player)
            }
            if (player.inventory.itemStack.isItemEqual(inventory[slotId])) {
                val ret = player.inventory.itemStack
                val transferCount = min(inventory[slotId].count, ret.maxStackSize - ret.count)
                ret.count += transferCount
                inventory[slotId].count -= transferCount
                return ret
            }

            return player.inventory.itemStack
        }
        return super.slotClick(slotId, dragType, clickTypeIn, player)
    }

    // Server Constructor
    init {
        val slotSizePlus2 = 18
        val startX = 8

        // Hotbar
        val hotbarY = 142
        for (column in 0..8) {
            addSlot(Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY))
        }

        // Main Player Inventory
        val startY = 84
        for (row in 0..2) {
            for (column in 0..8) {
                addSlot(Slot(playerInv, 9 + row * 9 + column, startX + column * slotSizePlus2,
                        startY + row * slotSizePlus2))
            }
        }

        // Furnace Slots
        addSlot(SlotItemHandler(tileEntityMarble.inventory, 0, 56, 34))
        addSlot(SlotItemHandler(tileEntityMarble.inventory, 1, 116, 35))
        trackInt(FunctionalIntReferenceHolder(IntSupplier { tileEntityMarble.currentSmeltTime },
                IntConsumer { value: Int -> tileEntityMarble.currentSmeltTime = value }).also { currentSmeltTime = it })
    }
}