package ru.rikgela.russianmagic.objects.items

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.text.StringTextComponent
import ru.rikgela.russianmagic.objects.mana.IManaTaker
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana

class RMLinkDestroyer(
        builder: Properties
) : Item(builder) {
    override fun onItemUse(context: ItemUseContext): ActionResultType {
        val world = context.world
        val blockPos = context.pos
        val playerEntity = context.player
        if (!world.isRemote) {
            val tileEntity = world.getTileEntity(blockPos)
            if (tileEntity is IManaTaker) {
                tileEntity.disconnectToManaSpreader()
                if (playerEntity is ServerPlayerEntity)
                    if (PlayerMana.fromPlayer(playerEntity).consume(30, playerEntity)) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format("Link was destroyed successfully")
                                )
                        )
                        return ActionResultType.SUCCESS
                    }
            }
        }
        return super.onItemUse(context)
    }
}