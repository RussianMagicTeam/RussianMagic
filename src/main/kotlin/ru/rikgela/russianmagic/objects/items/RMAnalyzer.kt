package ru.rikgela.russianmagic.objects.items

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.text.StringTextComponent
import ru.rikgela.russianmagic.objects.mana.IMana
import ru.rikgela.russianmagic.objects.mana.IManaReceiver
import ru.rikgela.russianmagic.objects.mana.IManaSpreader
import ru.rikgela.russianmagic.objects.mana.IManaTaker
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana

class RMAnalyzer(
        builder: Properties
) : Item(builder) {
    override fun onItemUse(context: ItemUseContext): ActionResultType {
        val world = context.world
        val blockPos = context.pos
        val playerEntity = context.player
        var actionResult = ActionResultType.FAIL
        if (!world.isRemote) {
            val tileEntity = world.getTileEntity(blockPos)
            if (playerEntity is ServerPlayerEntity)
                if (PlayerMana.fromPlayer(playerEntity).artificialConsume(100, playerEntity)) {
                    if (tileEntity is IMana) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format("Mana: "
                                                + tileEntity.currentMana.toString() + ":"
                                                + tileEntity.baseMaxMana.toString())
                                )
                        )
                        actionResult = ActionResultType.SUCCESS
                    }
                    if (tileEntity is IManaReceiver) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format("ManaReceiver: "
                                                + tileEntity.currentMana.toString() + ":"
                                                + tileEntity.baseMaxMana.toString())
                                )
                        )
                        actionResult = ActionResultType.SUCCESS
                    }
                    if (tileEntity is IManaSpreader) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format(
                                                "ManaSpreader: "
                                                        + tileEntity.currentMana.toString() + ":"
                                                        + tileEntity.baseMaxMana.toString()
                                        )
                                )
                        )
                        actionResult = ActionResultType.SUCCESS
                    }
                    if (tileEntity is IManaTaker) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format(
                                                "ManaTaker: "
                                                        + tileEntity.isConnectedToManaSpreader.toString() + ":"
                                                        + tileEntity.spreaderWorldPos
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