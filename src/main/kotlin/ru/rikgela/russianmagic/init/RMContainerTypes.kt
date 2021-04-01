package ru.rikgela.russianmagic.init

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.ContainerType
import net.minecraft.network.PacketBuffer
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.container.AbstractRMFurnaceContainer
import ru.rikgela.russianmagic.objects.container.RMFurnacesContainer

object RMContainerTypes {
    val CONTAINER_TYPES: DeferredRegister<ContainerType<*>> = DeferredRegister(ForgeRegistries.CONTAINERS, MOD_ID)

    @JvmField
    val RM_ONE_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_diamond_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMOneSupportOneToOneFurnaceContainer(windowID, playerInv, data)
                }
            }

    @JvmField
    val RM_TWO_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_isolated_diamond_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMTwoSupportOneToOneFurnaceContainer(windowID, playerInv, data)
                }
            }

    @JvmField
    val RM_THREE_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_ebony_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMThreeSupportOneToOneFurnaceContainer(windowID, playerInv, data)
                }
            }
}