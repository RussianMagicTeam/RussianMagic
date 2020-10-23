package ru.rikgela.russianmagic.container

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.PacketBuffer
import ru.rikgela.russianmagic.init.RMBlocks
import ru.rikgela.russianmagic.init.RMContainerTypes
import ru.rikgela.russianmagic.tileentity.AbstractRMFurnaceTileEntity

class RMEbonyFurnaceContainer(windowID: Int,
                              playerInv: PlayerInventory,
                              tileEntityFurnace: AbstractRMFurnaceTileEntity
) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_EBONY_FURNACE_CONTAINER.get(), RMBlocks.RM_EBONY_FURNACE_BLOCK.get(), playerInv, tileEntityFurnace) {

    // Client Constructor
    constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?) : this(windowID, playerInv, getTileEntity(playerInv, data))


}