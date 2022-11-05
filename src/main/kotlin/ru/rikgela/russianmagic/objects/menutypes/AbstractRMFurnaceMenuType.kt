package ru.rikgela.russianmagic.objects.menutypes

import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.*
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import ru.rikgela.russianmagic.init.RMBlocks
import ru.rikgela.russianmagic.objects.blockentities.AbstractRMFurnaceBlockEntity
import java.sql.DriverManager.println


abstract class AbstractRMFurnaceMenuType(
    id: Int,
    furnaceType: MenuType<*>,
    inv: Inventory,
    blockEntityFurnace: AbstractRMFurnaceBlockEntity?,
    data: ContainerData
) : AbstractContainerMenu(furnaceType, id) {

    var blockEntity: AbstractRMFurnaceBlockEntity?
    protected var level: Level
    private var data: ContainerData

    val isCrafting: Boolean
        get() = data.get(0) > 0


    // Max Progress
    val scaledProgress: Int
        get() {
            val progress = data[0]
            val maxProgress = data[1] // Max Progress
            val progressArrowSize = 26 // This is the height in pixels of your arrow
            return if (maxProgress != 0 && progress != 0) progress * progressArrowSize / maxProgress else 0
        }

    override fun quickMoveStack(playerIn: Player, index: Int): ItemStack {
        val sourceSlot: Slot = slots[index]
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY //EMPTY_ITEM
        val sourceStack = sourceSlot.item
        val copyOfSourceStack = sourceStack.copy()

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(
                    sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX,
                    TE_INVENTORY_FIRST_SLOT_INDEX
                            + TE_INVENTORY_SLOT_COUNT, false
                )
            ) {
                return ItemStack.EMPTY // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(
                    sourceStack,
                    VANILLA_FIRST_SLOT_INDEX,
                    VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT,
                    false
                )
            ) {
                return ItemStack.EMPTY
            }
        } else {
            println("Invalid slotIndex:$index")
            return ItemStack.EMPTY
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.count == 0) {
            sourceSlot.set(ItemStack.EMPTY)
        } else {
            sourceSlot.setChanged()
        }
        sourceSlot.onTake(playerIn, sourceStack)
        return copyOfSourceStack
    }

    override fun stillValid(player: Player): Boolean {
        return stillValid(
            ContainerLevelAccess.create(level, blockEntity!!.blockPos),
            player, RMBlocks.RM_ISOLATED_DIAMOND_FURNACE_BLOCK.get()
        )
    }

    protected fun addPlayerInventory(playerInventory: Inventory) {
        for (i in 0..2) {
            for (l in 0..8) {
                addSlot(Slot(playerInventory, l + (i * 9) + 9, 8 + l * 18, 86 + i * 18))
            }
        }
    }

    protected fun addPlayerHotbar(playerInventory: Inventory) {
        for (i in 0..8) {
            addSlot(Slot(playerInventory, i, 8 + i * 18, 144))
        }
    }

    companion object {
        // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
        // must assign a slot number to each of the slots used by the GUI.
        // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
        // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
        //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
        //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
        //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
        private val HOTBAR_SLOT_COUNT = 9
        private val PLAYER_INVENTORY_ROW_COUNT = 3
        private val PLAYER_INVENTORY_COLUMN_COUNT = 9
        private val PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT
        private val VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT
        private val VANILLA_FIRST_SLOT_INDEX = 0
        private val TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT

        // THIS YOU HAVE TO DEFINE!
        private val TE_INVENTORY_SLOT_COUNT = 3 // must be the number of slots you have!
    }
    init{
        this.blockEntity = blockEntityFurnace
        this.level = inv.player.level
        this.data = data
    }
}
//
//abstract class AbstractRMFurnaceMenuType(windowID: Int,
//                                         furnaceType: MenuType<AbstractRMFurnaceMenuType>,
//                                         playerInv: Inventory,
//                                         val tileEntityFurnace: AbstractRMFurnaceBlockEntity
//) : AbstractContainerMenu(furnaceType, windowID) {
//
//    private var currentSmeltTime: FunctionalIntReferenceHolder? = null
//
//    private val canInteractWithCallable: IWorldPosCallable = IWorldPosCallable.of(tileEntityFurnace.world!!, tileEntityFurnace.pos)
//
//    override fun canInteractWith(playerIn: PlayerEntity): Boolean {
//        return canInteractWithCallable.applyOrElse({ _: World, blockPos: BlockPos -> playerIn.getDistanceSq(blockPos.x.toDouble() + 0.5, blockPos.y.toDouble() + 0.5, blockPos.z.toDouble() + 0.5) <= 64.0 }, true)
//    }
//
//    private fun transferToInventory(player: PlayerEntity, index: Int): ItemStack {
//        val slot = inventorySlots[index]
//        for (i in 0..35) {
//            if (inventorySlots[i].hasStack && inventorySlots[i].stack.isItemEqual(slot.stack)) {
//                val transferCount = min(slot.stack.count, inventorySlots[i].stack.maxStackSize - inventorySlots[i].stack.count)
//                slot.stack.count -= transferCount
//                inventorySlots[i].stack.count += transferCount
//                if (slot.stack.isEmpty) return player.activeItemStack
//            }
//        }
//
//        for (i in 0..35) {
//            if (!inventorySlots[i].hasStack) {
//                inventorySlots[i].putStack(slot.stack)
//                inventorySlots[index].putStack(ItemStack.EMPTY)
//                return player.activeItemStack
//            }
//        }
//
//        return player.activeItemStack
//    }
//
//    @Nonnull
//    override fun transferStackInSlot(player: PlayerEntity, index: Int): ItemStack {
//
//        val returnStack = player.activeItemStack
//        val slot = inventorySlots[index]
//
//        if (slot.hasStack) {
//            if (index == 36 || index == 37) {
//                transferToInventory(player, index)
//            } else {
//                if (inventorySlots[36].hasStack && inventorySlots[36].stack.isItemEqual(slot.stack)) {
//                    val transferCount = min(slot.stack.count, inventorySlots[36].stack.maxStackSize - inventorySlots[36].stack.count)
//                    slot.stack.count -= transferCount
//                    inventorySlots[36].stack.count += transferCount
//                    if (slot.stack.isEmpty) return returnStack
//                } else if (!inventorySlots[36].hasStack) {
//                    inventorySlots[36].putStack(slot.stack)
//                    inventorySlots[index].putStack(ItemStack.EMPTY)
//                    return returnStack
//                }
//            }
//        }
//        return returnStack
//    }
//
//    @get:OnlyIn(Dist.CLIENT)
//    val smeltProgressionScaled: Int
//        get() = if (currentSmeltTime!!.get() != 0 && tileEntityFurnace.maxSmeltTime != 0) currentSmeltTime!!.get() * 24 / tileEntityFurnace.maxSmeltTime else 0
//
//    companion object {
//        fun getTileEntity(playerInv: PlayerInventory?, data: PacketBuffer?): AbstractRMFurnaceBlockEntity {
//            Objects.requireNonNull(playerInv, "playerInv cannot be null")
//            Objects.requireNonNull(data, "data cannot be null")
//            val tileAtPos = (playerInv!!).player.world.getTileEntity((data!!).readBlockPos())
//            if (tileAtPos is AbstractRMFurnaceBlockEntity) {
//                return tileAtPos
//            }
//            throw IllegalStateException("TileEntity is not correct $tileAtPos")
//        }
//    }
//
//    override fun slotClick(slotId: Int, dragType: Int, clickTypeIn: ClickType, player: PlayerEntity): ItemStack {
//        if(slotId == 37) {
//            if (player.inventory.itemStack.isEmpty) {
//                return super.slotClick(slotId, dragType, clickTypeIn, player)
//            }
//            if (player.inventory.itemStack.isItemEqual(inventory[slotId])) {
//                val ret = player.inventory.itemStack
//                val transferCount = min(inventory[slotId].count, ret.maxStackSize - ret.count)
//                ret.count += transferCount
//                inventory[slotId].count -= transferCount
//                return ret
//            }
//
//            return player.inventory.itemStack
//        }
//        return super.slotClick(slotId, dragType, clickTypeIn, player)
//    }
//
//    // Server Constructor
//    init {
//        val slotSizePlus2 = 18
//        val startX = 8
//
//        // Hotbar
//        val hotbarY = 142
//        for (column in 0..8) {
//            this.addSlot(Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY))
//        }
//
//        // Main Player Inventory
//        val startY = 84
//        for (row in 0..2) {
//            for (column in 0..8) {
//                this.addSlot(Slot(playerInv, 9 + row * 9 + column, startX + column * slotSizePlus2,
//                        startY + row * slotSizePlus2))
//            }
//        }
//        // Furnace Slots
//        this.trackInt(FunctionalIntReferenceHolder({ tileEntityFurnace.currentSmeltTime },
//                { value: Int -> tileEntityFurnace.currentSmeltTime = value }).also { currentSmeltTime = it })
//    }
//}