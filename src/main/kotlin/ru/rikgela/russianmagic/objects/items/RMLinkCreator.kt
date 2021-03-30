package ru.rikgela.russianmagic.objects.items

import net.minecraft.item.Item
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.StringTextComponent
import ru.rikgela.russianmagic.mana.IManaSpreader
import ru.rikgela.russianmagic.mana.IManaTaker

class RMLinkCreator(
        builder: Properties
) : Item(builder) {
    private var manaSpreaderPos: BlockPos = BlockPos(0, 0, 0)
    private var manaSpreaderWorldId: Int = 0
    override fun onItemUse(context: ItemUseContext): ActionResultType {
        val world = context.world
        val blockPos = context.pos
        val playerEntity = context.player
        if (!world.isRemote) {
            val tileEntity = world.getTileEntity(blockPos)
            if (tileEntity is IManaTaker) {
                tileEntity.connectToManaSpreader(manaSpreaderPos, world.server!!, manaSpreaderWorldId)
                (playerEntity!!).sendMessage(
                    StringTextComponent(
                        String.format("Link successfully created")
                    )
                )
                return ActionResultType.SUCCESS
            }
            if (tileEntity is IManaSpreader) {
                manaSpreaderPos = blockPos
                manaSpreaderWorldId = playerEntity?.dimension?.id ?: 0
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