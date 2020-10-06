package ru.rikgela.russianmagic

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.text.StringTextComponent
import net.minecraft.util.text.TextComponent
import net.minecraftforge.event.entity.EntityJoinWorldEvent
import net.minecraftforge.event.entity.living.LivingDeathEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

//CapabilityManager.INSTANCE.register(IManaHandler.class, new Storage(), DefaultManaHandler.class);

class MyForgeEventHandler {
    @SubscribeEvent
    fun pickupItem(event: EntityItemPickupEvent?) {
        println("Item picked up!")
        (event?.entity as ServerPlayerEntity)
                .sendMessage(StringTextComponent("You picked up something"))
    }
}



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
            if (player.getName().equals("_Ivasik_")) player.dropItem(ItemStack(Items.ENCHANTED_SAUSAGE.get(), 1, CompoundNBT()), false)
        }
    }
}
