package ru.rikgela.russianmagic.objects.blockentities

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.block.state.BlockState
import ru.rikgela.russianmagic.init.RMBlockEntityTypes
import ru.rikgela.russianmagic.network.RMMessages
import ru.rikgela.russianmagic.network.packet.ItemStackSyncS2CPacket
import ru.rikgela.russianmagic.network.packet.ManaSyncS2CPacket
import ru.rikgela.russianmagic.objects.blocks.RMMekanism
import ru.rikgela.russianmagic.objects.mana.IManaReceiver
import ru.rikgela.russianmagic.objects.menutypes.RMFurnacesMenuTypes

object RMFurnacesBlockEntity {
    class RMDiamondFurnaceBlockEntity(
        blockPos: BlockPos,
        blockState: BlockState
    ) : AbstractRMFurnaceBlockEntity(
        RMBlockEntityTypes.RM_DIAMOND_FURNACE.get(),
        blockPos,
        blockState,
        RMMekanism(
            1,
            RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.numberOfInputSlots,
            RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.numberOfOutputSlots,
            RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.numberOfSupportSlots,
            "rm_diamond_furnace",
            setOf(RecipeType.SMELTING)
        )
    ), IManaReceiver {
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
//            RMMessages.sendToClients(ManaSyncS2CPacket(this.mana, this.worldPosition))
//            RMMessages.sendToClients(ItemStackSyncS2CPacket(this.inventory, this.worldPosition))
            super.update()
            return RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType(windowID, playerInv, this, this.data)
        }
    }

    class RMIsolatedDiamondFurnaceBlockEntity(
        blockPos: BlockPos,
        blockState: BlockState
    ) : AbstractRMFurnaceBlockEntity(
        RMBlockEntityTypes.RM_ISOLATED_DIAMOND_FURNACE.get(),
        blockPos,
        blockState,
        RMMekanism(
            1,
            RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.numberOfInputSlots,
            RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.numberOfOutputSlots,
            RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.numberOfSupportSlots,
            "rm_isolated_diamond_furnace",
            setOf(RecipeType.SMELTING)
        )
    ), IManaReceiver {
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
            RMMessages.sendToClients(ManaSyncS2CPacket(this.mana, this.worldPosition))
            RMMessages.sendToClients(ItemStackSyncS2CPacket(this.inventory, this.worldPosition))
            return RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType(windowID, playerInv, this, this.data)
        }
    }

    class RMEbonyFurnaceBlockEntity(
        blockPos: BlockPos,
        blockState: BlockState
    ) : AbstractRMFurnaceBlockEntity(
        RMBlockEntityTypes.RM_EBONY_FURNACE.get(),
        blockPos,
        blockState,
        RMMekanism(
            1,
            RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.numberOfInputSlots,
            RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.numberOfOutputSlots,
            RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.numberOfSupportSlots,
            "rm_ebony_furnace",
            setOf(RecipeType.SMELTING)
        )
    ), IManaReceiver {
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
            RMMessages.sendToClients(ManaSyncS2CPacket(this.mana, this.worldPosition))
            RMMessages.sendToClients(ItemStackSyncS2CPacket(this.inventory, this.worldPosition))
            return RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType(windowID, playerInv, this, this.data)
        }
    }

    class RMMarbleFurnaceBlockEntity(
        blockPos: BlockPos,
        blockState: BlockState
    ) : AbstractRMFurnaceBlockEntity(
        RMBlockEntityTypes.RM_MARBLE_FURNACE.get(),
        blockPos,
        blockState,
        RMMekanism(
            2,
            RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.numberOfInputSlots,
            RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.numberOfOutputSlots,
            RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.numberOfSupportSlots,
            "rm_marble_furnace",
            setOf(RecipeType.SMELTING)
        )
    ), IManaReceiver {
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
            RMMessages.sendToClients(ManaSyncS2CPacket(this.mana, this.worldPosition))
            RMMessages.sendToClients(ItemStackSyncS2CPacket(this.inventory, this.worldPosition))
            return RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType(windowID, playerInv, this, this.data)
        }
    }

    class RMWhiteJadeFurnaceBlockEntity(
        blockPos: BlockPos,
        blockState: BlockState
    ) : AbstractRMFurnaceBlockEntity(
        RMBlockEntityTypes.RM_WHITE_JADE_FURNACE.get(),
        blockPos,
        blockState,
        RMMekanism(
            3,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfInputSlots,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfOutputSlots,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfSupportSlots,
            "rm_white_jade_furnace",
            setOf(RecipeType.SMELTING)
        )
    ), IManaReceiver {
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
            RMMessages.sendToClients(ManaSyncS2CPacket(this.mana, this.worldPosition))
            RMMessages.sendToClients(ItemStackSyncS2CPacket(this.inventory, this.worldPosition))
            return RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType(windowID, playerInv, this, this.data)
        }
    }

    class RMRhinestoneFurnaceBlockEntity(
        blockPos: BlockPos,
        blockState: BlockState
    ) : AbstractRMFurnaceBlockEntity(
        RMBlockEntityTypes.RM_RHINESTONE_FURNACE.get(),
        blockPos,
        blockState,
        RMMekanism(
            4,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfInputSlots,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfOutputSlots,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfSupportSlots,
            "rm_rhinestone_furnace",
            setOf(RecipeType.SMELTING)
        )
    ), IManaReceiver {
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
            RMMessages.sendToClients(ManaSyncS2CPacket(this.mana, this.worldPosition))
            RMMessages.sendToClients(ItemStackSyncS2CPacket(this.inventory, this.worldPosition))
            return RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType(windowID, playerInv, this, this.data)
        }
    }

    class RMAquamarineFurnaceBlockEntity(
        blockPos: BlockPos,
        blockState: BlockState
    ) : AbstractRMFurnaceBlockEntity(
        RMBlockEntityTypes.RM_AQUAMARINE_FURNACE.get(),
        blockPos,
        blockState,
        RMMekanism(
            5,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfInputSlots,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfOutputSlots,
            RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.numberOfSupportSlots,
            "rm_aquamarine_furnace",
            setOf(RecipeType.SMELTING)
        )
    ), IManaReceiver {
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
            RMMessages.sendToClients(ManaSyncS2CPacket(this.mana, this.worldPosition))
            RMMessages.sendToClients(ItemStackSyncS2CPacket(this.inventory, this.worldPosition))
            return RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType(windowID, playerInv, this, this.data)
        }
    }
}