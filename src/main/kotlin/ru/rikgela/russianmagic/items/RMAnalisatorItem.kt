package ru.rikgela.russianmagic.items

import net.minecraft.item.Item
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.text.StringTextComponent
import ru.rikgela.russianmagic.mana.IManaReceiver
import ru.rikgela.russianmagic.mana.IManaSpreader

class RMAnalisatorItem(
        builder: Properties
) : Item(builder) {
    var pos_x: Int = 0
    var pos_y: Int = 0
    var pos_z: Int = 0
    override fun onItemUse(context: ItemUseContext): ActionResultType {
        val world = context.world
        val blockPos = context.pos
        //val blockState = world.getBlockState(blockPos)
        val playerEntity = context.player
        if (!world.isRemote) {
            val tileentity = world.getTileEntity(blockPos)
            if (tileentity is IManaReceiver) {
                tileentity.setPositionOfMagicSource(pos_x, pos_y, pos_z)
                (playerEntity!!).sendMessage(
                        StringTextComponent(
                                String.format("Link successfully created")
                        )
                )
                return ActionResultType.SUCCESS
            }
            if (tileentity is IManaSpreader) {
                pos_x = blockPos.x
                pos_y = blockPos.y
                pos_z = blockPos.z
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