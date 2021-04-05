package ru.rikgela.russianmagic.objects.items

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.text.StringTextComponent
import ru.rikgela.russianmagic.objects.mana.*

class RMAnalyzer(
        builder: Properties
) : Item(builder) {
    override fun onItemUse(context: ItemUseContext): ActionResultType {
        val world = context.world
        val blockPos = context.pos
        val playerEntity = context.player
        var actionResult = ActionResultType.FAIL
        if (!world.isRemote) {
            val tileentity = world.getTileEntity(blockPos)
            if (playerEntity is ServerPlayerEntity)
                if (PlayerMana.fromPlayer(playerEntity).consume(100, playerEntity)) {
                    if (tileentity is IMana) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format("Mana: "
                                                + tileentity.currentMana.toString() + ":"
                                                + tileentity.baseMaxMana.toString())
                                )
                        )
                        actionResult = ActionResultType.SUCCESS
                    }
                    if (tileentity is IManaReceiver) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format("ManaReceiver: "
                                                + tileentity.currentMana.toString() + ":"
                                                + tileentity.baseMaxMana.toString())
                                )
                        )
                        actionResult = ActionResultType.SUCCESS
                    }
                    if (tileentity is IManaSpreader) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format(
                                                "ManaSpreader: "
                                                        + tileentity.currentMana.toString() + ":"
                                                        + tileentity.baseMaxMana.toString()
                                        )
                                )
                        )
                        actionResult = ActionResultType.SUCCESS
                    }
                    if (tileentity is IManaTaker) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format(
                                                "ManaTaker: "
                                                        + tileentity.isConnectedToManaSpreader.toString() + ":"
                                                        + tileentity.spreaderWorldPos
                                        )
                                )
                        )
                        actionResult = ActionResultType.SUCCESS
                    }
                    if (actionResult != ActionResultType.SUCCESS) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format("Nothing found")
                                )
                        )
                        actionResult = ActionResultType.SUCCESS
                    }
                }
        }
        return actionResult
    }
}