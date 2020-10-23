package ru.rikgela.russianmagic.container

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.ClickType
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.ContainerType
import net.minecraft.inventory.container.Slot
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketBuffer
import net.minecraft.util.IWorldPosCallable
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.items.SlotItemHandler
import ru.rikgela.russianmagic.init.RMBlocks
import ru.rikgela.russianmagic.init.RMContainerTypes
import ru.rikgela.russianmagic.tileentity.AbstractRMFurnaceTileEntity
import ru.rikgela.russianmagic.tileentity.RMMarbleFurnaceTileEntity
import ru.rikgela.russianmagic.util.FunctionalIntReferenceHolder
import java.util.*
import java.util.function.IntConsumer
import java.util.function.IntSupplier
import javax.annotation.Nonnull
import kotlin.math.min

class RMMarbleFurnaceContainer(windowID: Int,
                               playerInv: PlayerInventory,
                               tileEntityFurnace: AbstractRMFurnaceTileEntity
) : AbstractRMFurnaceContainer(windowID, RMContainerTypes.RM_MARBLE_FURNACE_CONTAINER.get() , playerInv, tileEntityFurnace) {
    private val canInteractWithCallable: IWorldPosCallable = IWorldPosCallable.of(tileEntityFurnace.world!!, tileEntityFurnace.pos)

    // Client Constructor
    constructor(windowID: Int, playerInv: PlayerInventory, data: PacketBuffer?) : this(windowID, playerInv, getTileEntity(playerInv, data))

    override fun canInteractWith(playerIn: PlayerEntity): Boolean {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, RMBlocks.RM_MARBLE_FURNACE_BLOCK.get())
    }

}