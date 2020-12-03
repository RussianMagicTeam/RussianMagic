package ru.rikgela.russianmagic.tileentity

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.tileentity.ITickableTileEntity
import net.minecraft.tileentity.TileEntityType
import ru.rikgela.russianmagic.container.RMFurnacesContainer
import ru.rikgela.russianmagic.init.RMTileEntityTypes
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.util.RMMekanism

object RMFurnacesTileEntity {
    class RMDiamondFurnaceTileEntity(rmMekanism: RMMekanism,
                                     tileEntityTypeIn: TileEntityType<*> =
                                             RMTileEntityTypes.RM_DIAMOND_FURNACE.get())
        : AbstractRMFurnaceTileEntity(tileEntityTypeIn, rmMekanism),
            ITickableTileEntity, INamedContainerProvider, IManaReceiver {
        override val SLOTS_UP = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_UP
        override val SLOTS_DOWN = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_DOWN
        override val SLOTS_HORIZONTAL = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_HORIZONTAL
        override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
            return RMFurnacesContainer.RMMarbleFurnaceContainer(windowID, playerInv, this)
        }
    }

    class RMIsolatedDiamondFurnaceTileEntity(rmMekanism: RMMekanism,
                                             tileEntityTypeIn: TileEntityType<*> =
                                                     RMTileEntityTypes.RM_ISOLATED_DIAMOND_FURNACE.get())
        : AbstractRMFurnaceTileEntity(tileEntityTypeIn, rmMekanism),
            ITickableTileEntity, INamedContainerProvider, IManaReceiver {
        override val SLOTS_UP = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_UP
        override val SLOTS_DOWN = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_DOWN
        override val SLOTS_HORIZONTAL = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_HORIZONTAL
        override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
            return RMFurnacesContainer.RMMarbleFurnaceContainer(windowID, playerInv, this)
        }
    }

    class RMEbonyFurnaceTileEntity(rmMekanism: RMMekanism,
                                   tileEntityTypeIn: TileEntityType<*> =
                                           RMTileEntityTypes.RM_EBONY_FURNACE.get())
        : AbstractRMFurnaceTileEntity(tileEntityTypeIn, rmMekanism),
            ITickableTileEntity, INamedContainerProvider, IManaReceiver {
        override val SLOTS_UP = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_UP
        override val SLOTS_DOWN = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_DOWN
        override val SLOTS_HORIZONTAL = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_HORIZONTAL
        override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
            return RMFurnacesContainer.RMMarbleFurnaceContainer(windowID, playerInv, this)
        }
    }

    class RMMarbleFurnaceTileEntity(rmMekanism: RMMekanism,
                                    tileEntityTypeIn: TileEntityType<*> =
                                            RMTileEntityTypes.RM_MARBLE_FURNACE.get())
        : AbstractRMFurnaceTileEntity(tileEntityTypeIn, rmMekanism),
            ITickableTileEntity, INamedContainerProvider, IManaReceiver {
        override val SLOTS_UP = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_UP
        override val SLOTS_DOWN = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_DOWN
        override val SLOTS_HORIZONTAL = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_HORIZONTAL
        override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
            return RMFurnacesContainer.RMMarbleFurnaceContainer(windowID, playerInv, this)
        }
    }

    class RMWhiteJadeFurnaceTileEntity(rmMekanism: RMMekanism,
                                       tileEntityTypeIn: TileEntityType<*> =
                                               RMTileEntityTypes.RM_WHITE_JADE_FURNACE.get())
        : AbstractRMFurnaceTileEntity(tileEntityTypeIn, rmMekanism),
            ITickableTileEntity, INamedContainerProvider, IManaReceiver {
        override val SLOTS_UP = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_UP
        override val SLOTS_DOWN = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_DOWN
        override val SLOTS_HORIZONTAL = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_HORIZONTAL
        override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
            return RMFurnacesContainer.RMMarbleFurnaceContainer(windowID, playerInv, this)
        }
    }

    class RMRhinestoneFurnaceTileEntity(rmMekanism: RMMekanism,
                                        tileEntityTypeIn: TileEntityType<*> =
                                                RMTileEntityTypes.RM_RHINESTONE_FURNACE.get())
        : AbstractRMFurnaceTileEntity(tileEntityTypeIn, rmMekanism),
            ITickableTileEntity, INamedContainerProvider, IManaReceiver {
        override val SLOTS_UP = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_UP
        override val SLOTS_DOWN = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_DOWN
        override val SLOTS_HORIZONTAL = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_HORIZONTAL
        override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
            return RMFurnacesContainer.RMMarbleFurnaceContainer(windowID, playerInv, this)
        }
    }

    class RMAquamarineFurnaceTileEntity(rmMekanism: RMMekanism,
                                        tileEntityTypeIn: TileEntityType<*> =
                                                RMTileEntityTypes.RM_AQUAMARINE_FURNACE.get())
        : AbstractRMFurnaceTileEntity(tileEntityTypeIn, rmMekanism),
            ITickableTileEntity, INamedContainerProvider, IManaReceiver {
        override val SLOTS_UP = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_UP
        override val SLOTS_DOWN = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_DOWN
        override val SLOTS_HORIZONTAL = RMFurnacesContainer.RMMarbleFurnaceContainer.SLOTS_HORIZONTAL
        override fun createMenu(windowID: Int, playerInv: PlayerInventory, playerIn: PlayerEntity): Container {
            return RMFurnacesContainer.RMMarbleFurnaceContainer(windowID, playerInv, this)
        }
    }
}