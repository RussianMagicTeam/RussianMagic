package ru.rikgela.russianmagic.objects.items

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResultType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.text.StringTextComponent
import ru.rikgela.russianmagic.objects.mana.IManaSpreader
import ru.rikgela.russianmagic.objects.mana.IManaTaker
import ru.rikgela.russianmagic.objects.player.mana.PlayerMana

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
                tileEntity.connectToManaSpreader(manaSpreaderPos, tileEntity.pos, world.server!!, manaSpreaderWorldId)
                if (playerEntity is ServerPlayerEntity) {
                    if (PlayerMana.fromPlayer(playerEntity).artificialConsume(100, playerEntity)) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format("Link successfully created")
                                )
                        )
                        return ActionResultType.SUCCESS
                    }
                }
            }
            if (tileEntity is IManaSpreader) {
                manaSpreaderPos = blockPos
                manaSpreaderWorldId = playerEntity?.dimension?.id ?: 0
                if (playerEntity is ServerPlayerEntity) {
                    if (PlayerMana.fromPlayer(playerEntity).artificialConsume(100, playerEntity)) {
                        (playerEntity).sendMessage(
                                StringTextComponent(
                                        String.format("Spacetime coordinates saved")
                                )
                        )
                        return ActionResultType.SUCCESS
                    }
                }
            }
        }
        return super.onItemUse(context)
    }
}