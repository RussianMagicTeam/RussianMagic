package ru.rikgela.russianmagic.objects.container

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.ClickType
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.ContainerType
import net.minecraft.inventory.container.Slot
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.IWorldPosCallable
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.objects.tileentity.AbstractRMFurnaceTileEntity
import ru.rikgela.russianmagic.util.FunctionalIntReferenceHolder
import java.util.*
import javax.annotation.Nonnull
import kotlin.math.min

abstract class AbstractRMFurnaceContainer(windowID: Int,
                                          furnaceType: ContainerType<AbstractRMFurnaceContainer>,
                                          playerInv: PlayerInventory,
                                          val tileEntityFurnace: AbstractRMFurnaceTileEntity
) : Container(furnaceType, windowID) {

    private var currentSmeltTime: FunctionalIntReferenceHolder? = null

    private val canInteractWithCallable: IWorldPosCallable = IWorldPosCallable.of(tileEntityFurnace.world!!, tileEntityFurnace.pos)

    override fun canInteractWith(playerIn: PlayerEntity): Boolean {
        return canInteractWithCallable.applyOrElse({ _: World, blockPos: BlockPos -> playerIn.getDistanceSq(blockPos.x.toDouble() + 0.5, blockPos.y.toDouble() + 0.5, blockPos.z.toDouble() + 0.5) <= 64.0 }, true)
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

        val returnStack = player.activeItemStack
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
        get() = if (currentSmeltTime!!.get() != 0 && tileEntityFurnace.maxSmeltTime != 0) currentSmeltTime!!.get() * 24 / tileEntityFurnace.maxSmeltTime else 0

    companion object {
        fun getTileEntity(playerInv: PlayerInventory?, data: PacketBuffer?): AbstractRMFurnaceTileEntity {
            Objects.requireNonNull(playerInv, "playerInv cannot be null")
            Objects.requireNonNull(data, "data cannot be null")
            val tileAtPos = (playerInv!!).player.world.getTileEntity((data!!).readBlockPos())
            if (tileAtPos is AbstractRMFurnaceTileEntity) {
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
            this.addSlot(Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY))
        }

        // Main Player Inventory
        val startY = 84
        for (row in 0..2) {
            for (column in 0..8) {
                this.addSlot(Slot(playerInv, 9 + row * 9 + column, startX + column * slotSizePlus2,
                        startY + row * slotSizePlus2))
            }
        }
        // Furnace Slots
        this.trackInt(FunctionalIntReferenceHolder({ tileEntityFurnace.currentSmeltTime },
                { value: Int -> tileEntityFurnace.currentSmeltTime = value }).also { currentSmeltTime = it })
    }
}