package ru.rikgela.russianmagic.init

import net.minecraft.world.inventory.MenuType
import net.minecraftforge.common.extensions.IForgeMenuType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.menutypes.RMFurnacesMenuTypes.RMOneSupportOneToOneFurnaceMenuType
import ru.rikgela.russianmagic.objects.menutypes.RMFurnacesMenuTypes.RMThreeSupportOneToOneFurnaceMenuType
import ru.rikgela.russianmagic.objects.menutypes.RMFurnacesMenuTypes.RMTwoSupportOneToOneFurnaceMenuType


object RMMenuTypes {
    val MENU_TYPES: DeferredRegister<MenuType<*>> = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MOD_ID)

    val RM_ONE_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE: RegistryObject<MenuType<RMOneSupportOneToOneFurnaceMenuType>> =
        MENU_TYPES.register("rm_one_support_one_to_one_furnace_menu_type") {
                IForgeMenuType.create {
                        int, inventory, friendlyBuff ->
                    RMOneSupportOneToOneFurnaceMenuType(int, inventory, friendlyBuff)
                }
        }

    val RM_TWO_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE: RegistryObject<MenuType<RMTwoSupportOneToOneFurnaceMenuType>> =
        MENU_TYPES.register("rm_two_support_one_to_one_furnace_menu_type") {
            IForgeMenuType.create {
                    int, inventory, friendlyBuff ->
                RMTwoSupportOneToOneFurnaceMenuType(int, inventory, friendlyBuff)
            }
        }

    val RM_THREE_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE: RegistryObject<MenuType<RMThreeSupportOneToOneFurnaceMenuType>> =
        MENU_TYPES.register("rm_three_support_one_to_one_furnace_menu_type") {
            IForgeMenuType.create {
                    int, inventory, friendlyBuff ->
                RMThreeSupportOneToOneFurnaceMenuType(int, inventory, friendlyBuff)
            }
        }

}