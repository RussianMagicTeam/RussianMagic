package ru.rikgela.russianmagic.container

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.Slot
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.IWorldPosCallable
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.items.SlotItemHandler
import ru.rikgela.russianmagic.init.BlocksInit
import ru.rikgela.russianmagic.init.RMContainerTypes
import ru.rikgela.russianmagic.tileentity.RMFurnaceTileEntity
import ru.rikgela.russianmagic.util.FunctionalIntReferenceHolder
import java.util.*
import java.util.function.IntConsumer
import java.util.function.IntSupplier
import javax.annotation.Nonnull

class RMFurnaceContainer(windowID: Int, playerInv: PlayerInventory?,
                         var tileEntity: RMFurnaceTileEntity) : Container(RMContainerTypes.EXAMPLE_FURNACE.get(), windowID) {
    private val canInteractWithCallable: IWorldPosCallable
    var currentSmeltTime: FunctionalIntReferenceHolder? = null

    // Client Constructor
    constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer) : this(windowID, playerInv, getTileEntity(playerInv, data)) {}

    override fun canInteractWith(playerIn: PlayerEntity): Boolean {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, BlocksInit.RM_FURNACE_BLOCK.get())
    }

    @Nonnull
    override fun transferStackInSlot(player: PlayerEntity, index: Int): ItemStack {
        var returnStack = ItemStack.EMPTY
        val slot = inventorySlots[index]
        if (slot != null && slot.hasStack) {
            val slotStack = slot.stack
            returnStack = slotStack.copy()
            val containerSlots = inventorySlots.size - player.inventory.mainInventory.size
            if (index < containerSlots) {
                if (!mergeItemStack(slotStack, containerSlots, inventorySlots.size, true)) {
                    return ItemStack.EMPTY
                }
            } else if (!mergeItemStack(slotStack, 0, containerSlots, false)) {
                return ItemStack.EMPTY
            }
            if (slotStack.count == 0) {
                slot.putStack(ItemStack.EMPTY)
            } else {
                slot.onSlotChanged()
            }
            if (slotStack.count == returnStack.count) {
                return ItemStack.EMPTY
            }
            slot.onTake(player, slotStack)
        }
        return returnStack
    }

    @get:OnlyIn(Dist.CLIENT)
    val smeltProgressionScaled: Int
        get() = if (currentSmeltTime!!.get() != 0 && tileEntity.maxSmeltTime != 0) currentSmeltTime!!.get() * 24 / tileEntity.maxSmeltTime else 0

    companion object {
        private fun getTileEntity(playerInv: PlayerInventory, data: PacketBuffer): RMFurnaceTileEntity {
            Objects.requireNonNull(playerInv, "playerInv cannot be null")
            Objects.requireNonNull(data, "data cannot be null")
            val tileAtPos = playerInv.player.world.getTileEntity(data.readBlockPos())
            if (tileAtPos is RMFurnaceTileEntity) {
                return tileAtPos
            }
            throw IllegalStateException("TileEntity is not correct $tileAtPos")
        }
    }

    // Server Constructor
    init {
        canInteractWithCallable = IWorldPosCallable.of(tileEntity.world, tileEntity.pos)
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
        addSlot(SlotItemHandler(tileEntity.inventory, 0, 56, 34))
        addSlot(SlotItemHandler(tileEntity.inventory, 1, 116, 35))
        trackInt(FunctionalIntReferenceHolder(IntSupplier { tileEntity.currentSmeltTime },
                IntConsumer { value: Int -> tileEntity.currentSmeltTime = value }).also { currentSmeltTime = it })
    }
}