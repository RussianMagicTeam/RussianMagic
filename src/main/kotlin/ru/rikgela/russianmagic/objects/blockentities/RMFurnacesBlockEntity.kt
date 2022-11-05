package ru.rikgela.russianmagic.objects.blockentities

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.block.state.BlockState
import ru.rikgela.russianmagic.init.RMBlockEntityTypes
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
        RMMekanism(1, 1, "rm_isolated_diamond_furnace", setOf(RecipeType.SMELTING))
    ), IManaReceiver {
        override val upSlots = RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.upSlots
        override val downSlots = RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.downSlots
        override val horizontalSlots = RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.horizontalSlots
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
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
        RMMekanism(1, 1, "rm_isolated_diamond_furnace", setOf(RecipeType.SMELTING))
    ), IManaReceiver {
        override val upSlots = RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.upSlots
        override val downSlots = RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.downSlots
        override val horizontalSlots = RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType.horizontalSlots
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
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
        RMMekanism(1, 2, "rm_ebony_furnace", setOf(RecipeType.SMELTING))
    ), IManaReceiver {
        override val upSlots = RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.upSlots
        override val downSlots = RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.downSlots
        override val horizontalSlots = RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.horizontalSlots
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
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
        RMMekanism(2, 2, "rm_marble_furnace", setOf(RecipeType.SMELTING))
    ), IManaReceiver {
        override val upSlots = RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.upSlots
        override val downSlots = RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.downSlots
        override val horizontalSlots = RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType.horizontalSlots
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
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
        RMMekanism(3, 3, "rm_white_jade_furnace", setOf(RecipeType.SMELTING))
    ), IManaReceiver {
        override val upSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.upSlots
        override val downSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.downSlots
        override val horizontalSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.horizontalSlots
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
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
        RMMekanism(4, 3, "rm_rhinestone_furnace", setOf(RecipeType.SMELTING))
    ), IManaReceiver {
        override val upSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.upSlots
        override val downSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.downSlots
        override val horizontalSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.horizontalSlots
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
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
        RMMekanism(5, 3, "rm_aquamarine_furnace", setOf(RecipeType.SMELTING))
    ), IManaReceiver {
        override val upSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.upSlots
        override val downSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.downSlots
        override val horizontalSlots = RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType.horizontalSlots
        override fun createMenu(windowID: Int, playerInv: Inventory, playerIn: Player): AbstractContainerMenu {
            return RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType(windowID, playerInv, this, this.data)
        }
    }
}