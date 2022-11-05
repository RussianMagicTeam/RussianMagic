package ru.rikgela.russianmagic.init

import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.resources.ResourceLocation
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.objects.menutypes.AbstractRMFurnaceMenuType
import ru.rikgela.russianmagic.objects.screens.RMFurnaceScreen

object RMScreens {
    fun setupScreens() {
        MenuScreens.register(
            RMMenuTypes.RM_ONE_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE.get(),
            MenuScreens.ScreenConstructor<AbstractRMFurnaceMenuType, RMFurnaceScreen>{
                    screenContainer, inv, titleIn ->
                RMFurnaceScreen(
                    screenContainer,
                    inv,
                    titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace1_screen.png")
                )
            }
        )

        MenuScreens.register(
            RMMenuTypes.RM_TWO_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE.get(),
            MenuScreens.ScreenConstructor<AbstractRMFurnaceMenuType, RMFurnaceScreen>{
                    screenContainer, inv, titleIn ->
                RMFurnaceScreen(
                    screenContainer,
                    inv,
                    titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace2_screen.png")
                )
            }
        )
        MenuScreens.register(
            RMMenuTypes.RM_THREE_SUPPORT_ONE_TO_ONE_FURNACE_MENU_TYPE.get(),
            MenuScreens.ScreenConstructor<AbstractRMFurnaceMenuType, RMFurnaceScreen>{
                    screenContainer, inv, titleIn ->
                RMFurnaceScreen(
                    screenContainer,
                    inv,
                    titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace3_screen.png")
                )
            }
        )
//        MenuScreens.register(
//            RMMenuTypes.RM_THREE_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER.get(),
//            MenuScreens.ScreenConstructor { RMFurnaceScreen() }
//        )
    }
}