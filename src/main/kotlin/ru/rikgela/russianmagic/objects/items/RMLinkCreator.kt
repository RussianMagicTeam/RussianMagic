package ru.rikgela.russianmagic.objects.items

import net.minecraft.item.Item
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.StringTextComponent
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.mana.IManaSpreader

class RMLinkCreator(
        builder: Properties
) : Item(builder) {
    var magicSourcePos: BlockPos = BlockPos(0, 0, 0)
    override fun onItemUse(context: ItemUseContext): ActionResultType {
        val world = context.world
        val blockPos = context.pos
        val playerEntity = context.player
        if (!world.isRemote) {
            val tileentity = world.getTileEntity(blockPos)
            if (tileentity is IManaReceiver) {
                tileentity.setPositionOfMagicSource(magicSourcePos)
                (playerEntity!!).sendMessage(
                        StringTextComponent(
                                String.format("Link successfully created")
                        )
                )
                return ActionResultType.SUCCESS
            }
            if (tileentity is IManaSpreader) {
                magicSourcePos = blockPos
                (playerEntity!!).sendMessage(
                        StringTextComponent(
                                String.format("Spacetime coordinates saved")
                        )
                )
                return ActionResultType.SUCCESS
            }
        }
        return super.onItemUse(context)
    }
}