package ru.rikgela.russianmagic.init

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.ContainerType
import net.minecraft.network.PacketBuffer
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.fml.RegistryObject
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.container.AbstractRMFurnaceContainer
import ru.rikgela.russianmagic.container.RMFurnacesContainer

object RMContainerTypes {
    val CONTAINER_TYPES: DeferredRegister<ContainerType<*>> = DeferredRegister(ForgeRegistries.CONTAINERS, MOD_ID)

    @JvmField
    val RM_DIAMOND_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_diamond_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMDiamondFurnaceContainer(windowID, playerInv, data) as AbstractRMFurnaceContainer
                }
            }

    @JvmField
    val RM_ISOLATED_DIAMOND_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_isolated_diamond_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMIsolatedDiamondFurnaceContainer(windowID, playerInv, data) as AbstractRMFurnaceContainer
                }
            }

    @JvmField
    val RM_EBONY_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_ebony_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMEbonyFurnaceContainer(windowID, playerInv, data) as AbstractRMFurnaceContainer
                }
            }

    @JvmField
    val RM_MARBLE_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_marble_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMMarbleFurnaceContainer(windowID, playerInv, data) as AbstractRMFurnaceContainer
                }
            }

    @JvmField
    val RM_WHITE_JADE_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_white_jade_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMWhiteJadeFurnaceContainer(windowID, playerInv, data) as AbstractRMFurnaceContainer
                }
            }

    @JvmField
    val RM_RHINESTONE_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_rhinestone_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMRhinestoneFurnaceContainer(windowID, playerInv, data) as AbstractRMFurnaceContainer
                }
            }

    @JvmField
    val RM_AQUAMARINE_FURNACE_CONTAINER: RegistryObject<ContainerType<AbstractRMFurnaceContainer>> = CONTAINER_TYPES
            .register("rm_aquamarine_furnace") {
                IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer ->
                    RMFurnacesContainer.RMAquamarineFurnaceContainer(windowID, playerInv, data) as AbstractRMFurnaceContainer
                }
            }
}