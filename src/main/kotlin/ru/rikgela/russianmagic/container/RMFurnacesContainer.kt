package ru.rikgela.russianmagic.container

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.PacketBuffer
import net.minecraftforge.items.SlotItemHandler
import ru.rikgela.russianmagic.init.RMContainerTypes
import ru.rikgela.russianmagic.tileentity.AbstractRMFurnaceTileEntity

object RMFurnacesContainer {

    class RMIsolatedDiamondFurnaceContainer(windowID: Int,
                                            playerInv: PlayerInventory,
                                            tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_ISOLATED_DIAMOND_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val SLOTS_UP = intArrayOf(0)
            val SLOTS_DOWN = intArrayOf(1)
            val SLOTS_HORIZONTAL = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
        }
    }

    class RMDiamondFurnaceContainer(windowID: Int,
                                    playerInv: PlayerInventory,
                                    tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_DIAMOND_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val SLOTS_UP = intArrayOf(0)
            val SLOTS_DOWN = intArrayOf(1)
            val SLOTS_HORIZONTAL = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
        }
    }

    class RMEbonyFurnaceContainer(windowID: Int,
                                  playerInv: PlayerInventory,
                                  tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_EBONY_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val SLOTS_UP = intArrayOf(0)
            val SLOTS_DOWN = intArrayOf(1)
            val SLOTS_HORIZONTAL = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
        }
    }

    class RMMarbleFurnaceContainer(windowID: Int,
                                   playerInv: PlayerInventory,
                                   tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_MARBLE_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val SLOTS_UP = intArrayOf(0)
            val SLOTS_DOWN = intArrayOf(1)
            val SLOTS_HORIZONTAL = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
        }
    }

    class RMWhiteJadeFurnaceContainer(windowID: Int,
                                      playerInv: PlayerInventory,
                                      tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_WHITE_JADE_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val SLOTS_UP = intArrayOf(0)
            val SLOTS_DOWN = intArrayOf(1)
            val SLOTS_HORIZONTAL = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
        }
    }

    class RMRhinestoneFurnaceContainer(windowID: Int,
                                       playerInv: PlayerInventory,
                                       tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_RHINESTONE_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val SLOTS_UP = intArrayOf(0)
            val SLOTS_DOWN = intArrayOf(1)
            val SLOTS_HORIZONTAL = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
        }
    }

    class RMAquamarineFurnaceContainer(windowID: Int,
                                       playerInv: PlayerInventory,
                                       tileEntityFurnace: AbstractRMFurnaceTileEntity,
    ) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_AQUAMARINE_FURNACE_CONTAINER.get(), playerInv, tileEntityFurnace) {

        // Client Constructor
        constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?)
                : this(windowID, playerInv,
                getTileEntity(playerInv, data))

        companion object {
            val SLOTS_UP = intArrayOf(0)
            val SLOTS_DOWN = intArrayOf(1)
            val SLOTS_HORIZONTAL = intArrayOf(0)
        }

        init {
            // Furnace Slots
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 0, 56, 34))
            addSlot(SlotItemHandler(tileEntityFurnace.inventory, 1, 116, 35))
        }
    }

}