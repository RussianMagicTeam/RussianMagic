package ru.rikgela.russianmagic.init

import net.minecraft.client.gui.ScreenManager
import net.minecraft.client.renderer.entity.EntityRendererManager
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.client.registry.RenderingRegistry
import ru.rikgela.russianmagic.MOD_ID
import ru.rikgela.russianmagic.client.HUDEventHandler
import ru.rikgela.russianmagic.client.block.AbstractRMMagicSourceTileEntityRenderer
import ru.rikgela.russianmagic.client.entity.ProjectileEntityRender
import ru.rikgela.russianmagic.client.gui.RMFurnaceScreen

object RMRenderInit {
    fun setupScreens() {
        MinecraftForge.EVENT_BUS.register(HUDEventHandler())
        ScreenManager.registerFactory(RMContainerTypes.RM_ONE_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace1_screen.png"))
        }
        ScreenManager.registerFactory(RMContainerTypes.RM_TWO_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(
                    screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace2_screen.png")
            )
        }
        ScreenManager.registerFactory(RMContainerTypes.RM_THREE_SUPPORT_ONE_TO_ONE_FURNACE_CONTAINER.get()) { screenContainer, inv, titleIn ->
            RMFurnaceScreen(
                    screenContainer, inv, titleIn,
                    ResourceLocation(MOD_ID, "textures/gui/rm_furnace3_screen.png")
            )
        }
        RenderingRegistry.registerEntityRenderingHandler(RMEntities.PROJECTILE_ENTITY.get()) { renderManagerIn: EntityRendererManager ->
            ProjectileEntityRender(
                    renderManagerIn, ResourceLocation(MOD_ID, "textures/entity/projectile_entity.png")
            )
        }
        ClientRegistry.bindTileEntityRenderer(RMTileEntityTypes.RM_BASIC_MAGIC_SOURCE.get()) { renderManagerIn: TileEntityRendererDispatcher -> AbstractRMMagicSourceTileEntityRenderer(renderManagerIn) }

    }
}