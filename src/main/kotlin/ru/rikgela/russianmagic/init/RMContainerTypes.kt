package ru.rikgela.russianmagic.init

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.ContainerType
import net.minecraft.network.PacketBuffer
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.container.RMMarbleFurnaceContainer

object RMContainerTypes {
    val CONTAINER_TYPES: DeferredRegister<ContainerType<*>> = DeferredRegister(ForgeRegistries.CONTAINERS, MOD_ID)

    @JvmField
    val RM_MARBLE_FURNACE_CONTAINER: RegistryObject<ContainerType<RMMarbleFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_marble_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMMarbleFurnaceContainer(windowID, playerInv, data)
                }
            }
}