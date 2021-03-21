package ru.rikgela.russianmagic.objects.items

import net.minecraft.item.Item
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.text.StringTextComponent
import ru.rikgela.russianmagic.mana.IMana
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.mana.IManaSpreader
import ru.rikgela.russianmagic.mana.IManaTaker

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
            if (tileentity is IMana) {
                (playerEntity!!).sendMessage(
                        StringTextComponent(
                                String.format("Mana: "
                                        + tileentity.currentMana.toString() + ":"
                                        + tileentity.maxMana.toString())
                        )
                )
                actionResult = ActionResultType.SUCCESS
            }
            if (tileentity is IManaReceiver) {
                (playerEntity!!).sendMessage(
                        StringTextComponent(
                                String.format("ManaReceiver: "
                                        + tileentity.currentMana.toString() + ":"
                                        + tileentity.maxMana.toString())
                        )
                )
                actionResult = ActionResultType.SUCCESS
            }
            if (tileentity is IManaSpreader) {
                (playerEntity!!).sendMessage(
                    StringTextComponent(
                        String.format(
                            "ManaSpreader: "
                                    + tileentity.currentMana.toString() + ":"
                                    + tileentity.maxMana.toString()
                        )
                    )
                )
                actionResult = ActionResultType.SUCCESS
            }
            if (tileentity is IManaTaker) {
                (playerEntity!!).sendMessage(
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
        }
        return actionResult
        //return super.onItemUse(context)
    }
}