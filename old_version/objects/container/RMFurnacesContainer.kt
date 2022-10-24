package ru.rikgela.russianmagic.objects.container

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.PacketBuffer
import net.minecraftforge.items.SlotItemHandler
import ru.rikgela.russianmagic.init.RMContainerTypes
import ru.rikgela.russianmagic.objects.tileentity.AbstractRMFurnaceTileEntity

object RMFurnacesContainer {

    class RMOneSupportOneToOneFurnaceContainer(windowID: Int,
                                               playerInv: PlayerInventory,
                                               tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_ONE_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val upSlots = intArrayOf(0)
            val downSlots = intArrayOf(1)
            val horizontalSlots = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
            // Support Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 2, 22, 34))
        }
    }

    class RMTwoSupportOneToOneFurnaceContainer(windowID: Int,
                                               playerInv: PlayerInventory,
                                               tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_TWO_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val upSlots = intArrayOf(0)
            val downSlots = intArrayOf(1)
            val horizontalSlots = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
            // Support Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 2, 22, 24))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 3, 22, 44))
        }
    }

    class RMThreeSupportOneToOneFurnaceContainer(windowID: Int,
                                                 playerInv: PlayerInventory,
                                                 tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_THREE_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val upSlots = intArrayOf(0)
            val downSlots = intArrayOf(1)
            val horizontalSlots = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
            // Support Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 2, 22, 15))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 3, 22, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 4, 22, 53))
        }
    }

}