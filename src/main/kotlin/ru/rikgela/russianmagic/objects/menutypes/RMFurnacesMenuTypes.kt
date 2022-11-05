package ru.rikgela.russianmagic.objects.menutypes

import net.minecraft.network.FriendlyByteBuf
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.inventory.SimpleContainerData
import net.minecraftforge.common.capabilities.ForgeCapabilities
import net.minecraftforge.items.SlotItemHandler
import ru.rikgela.russianmagic.init.RMMenuTypes
import ru.rikgela.russianmagic.objects.blockentities.AbstractRMFurnaceBlockEntity

object RMFurnacesMenuTypes {

    class RMOneSupportOneToOneFurnaceMenuType(
        windowID: Int,
        inv: Inventory,
        blockEntityFurnace: AbstractRMFurnaceBlockEntity,
        data: ContainerData
    ) : AbstractRMFurnaceMenuType(
        windowID,
        RMMenuTypes.RM_ONE_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE.get(),
        inv,
        blockEntityFurnace,
        data
    ) {

        constructor(id: Int, inv: Inventory, extraData: FriendlyByteBuf) : this(
            id,
            inv,
            inv.player.level.getBlockEntity(extraData.readBlockPos()) as AbstractRMFurnaceBlockEntity,
            SimpleContainerData(2)
        )

        companion object {
            val upSlots = intArrayOf(0)
            val downSlots = intArrayOf(1)
            val horizontalSlots = intArrayOf(0)
        }

        init {
            checkContainerSize(inv, 3)
            addPlayerInventory(inv)
            addPlayerHotbar(inv)
            blockEntity!!.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent { handler ->
                // Furnace Slots
                addSlot(SlotItemHandler(handler, 0, 56, 34))
                addSlot(SlotItemHandler(handler, 1, 116, 35))
                // Support Slots
                addSlot(SlotItemHandler(handler, 2, 22, 34))
            }
            addDataSlots(data)
        }
    }

    class RMTwoSupportOneToOneFurnaceMenuType(
        windowID: Int,
        inv: Inventory,
        blockEntityFurnace: AbstractRMFurnaceBlockEntity,
        data: ContainerData
    ) : AbstractRMFurnaceMenuType(
        windowID,
        RMMenuTypes.RM_TWO_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE.get(),
        inv,
        blockEntityFurnace, data
    ) {

        constructor(id: Int, inv: Inventory, extraData: FriendlyByteBuf) : this(
            id,
            inv,
            inv.player.level.getBlockEntity(extraData.readBlockPos()) as AbstractRMFurnaceBlockEntity,
            SimpleContainerData(2)
        )

        companion object {
            val upSlots = intArrayOf(0)
            val downSlots = intArrayOf(1)
            val horizontalSlots = intArrayOf(0)
        }

        init {
            checkContainerSize(inv, 3)
            addPlayerInventory(inv)
            addPlayerHotbar(inv)
            blockEntity!!.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent { handler ->
                // Furnace Slots
                addSlot(SlotItemHandler(handler, 0, 56, 34))
                addSlot(SlotItemHandler(handler, 1, 116, 35))
                // Support Slots
                addSlot(SlotItemHandler(handler, 2, 22, 24))
                addSlot(SlotItemHandler(handler, 3, 22, 44))
            }
            addDataSlots(data)
        }
    }

    class RMThreeSupportOneToOneFurnaceMenuType(
        windowID: Int,
        inv: Inventory,
        blockEntityFurnace: AbstractRMFurnaceBlockEntity,
        data: ContainerData
    ) : AbstractRMFurnaceMenuType(
        windowID,
        RMMenuTypes.RM_THREE_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE.get(),
        inv,
        blockEntityFurnace, data
    ) {

        constructor(id: Int, inv: Inventory, extraData: FriendlyByteBuf) : this(
            id,
            inv,
            inv.player.level.getBlockEntity(extraData.readBlockPos()) as AbstractRMFurnaceBlockEntity,
            SimpleContainerData(2)
        )

        companion object {
            val upSlots = intArrayOf(0)
            val downSlots = intArrayOf(1)
            val horizontalSlots = intArrayOf(0)
        }

        init {
            checkContainerSize(inv, 3)
            addPlayerInventory(inv)
            addPlayerHotbar(inv)
            blockEntity!!.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent { handler ->
                // Furnace Slots
                addSlot(SlotItemHandler(handler, 0, 56, 34))
                addSlot(SlotItemHandler(handler, 1, 116, 35))
                // Support Slots
                addSlot(SlotItemHandler(handler, 2, 22, 24))
                addSlot(SlotItemHandler(handler, 3, 22, 44))
                addSlot(SlotItemHandler(handler, 4, 22, 53))
            }
            addDataSlots(data)
        }
    }

}