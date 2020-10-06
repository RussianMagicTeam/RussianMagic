package ru.rikgela.russianmagic

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.eventbus.api.SubscribeEvent


//CapabilityManager.INSTANCE.register(IManaHandler.class, new Storage(), DefaultManaHandler.class);

/*@SubscribeEvent
class MyForgeEventHandler {
    @SubscribeEvent
    fun pickupItem(event: EntityItemPickupEvent?) {
        println("Item picked up!")
    }
}

 */

class EventsHandler {
    @SubscribeEvent
    fun onJoin(e: EntityJoinWorldEvent) {
        if (e.entity is PlayerEntity) {
            val player: PlayerEntity = e.entity as PlayerEntity
            //val tmp = "Hello, " + player.getName() + "!"
            //ITextComponent text  = new TextComponentTranslation(tmp)

        }
    }

    @SubscribeEvent
    fun onDeath(e: LivingDeathEvent) {
        if (e.entity is PlayerEntity) {
            val player: PlayerEntity = e.entity as PlayerEntity
            if (player.getName().equals("_Ivasik_")) player.dropItem(ItemStack(Items.GOLDEN_APPLE, 1, 1), false)
        }
    }
}
