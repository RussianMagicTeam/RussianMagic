package ru.rikgela.russianmagic.init

import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.PacketBuffer
import net.minecraftforge.common.extensions.IForgeContainerType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.container.RMFurnaceContainer

object RMContainerTypes {
    public val CONTAINER_TYPES = DeferredRegister(
            ForgeRegistries.CONTAINERS, MOD_ID)

    //public static final RegistryObject<ContainerType<ExampleChestContainer>> EXAMPLE_CHEST = CONTAINER_TYPES
    //        .register("example_chest", () -> IForgeContainerType.create(ExampleChestContainer::new));
    @JvmField
    val RM_FURNACE_CONTAINER = CONTAINER_TYPES
            .register("rm_furnace") { IForgeContainerType.create { windowID: Int, playerInv: PlayerInventory, data: PacketBuffer -> RMFurnaceContainer(windowID, playerInv, data) } }

}