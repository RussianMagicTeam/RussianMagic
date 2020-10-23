package ru.rikgela.russianmagic.objects.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.item.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.container.Container
import net.minecraft.item.BlockItemUseContext
import net.minecraft.item.ItemStack
import net.minecraft.particles.ParticleTypes
import net.minecraft.state.BooleanProperty
import net.minecraft.state.DirectionProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.*
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.common.RMCCMessage
import ru.rikgela.russianmagic.init.RMTileEntityTypes
import ru.rikgela.russianmagic.tileentity.RMMarbleFurnaceTileEntity
import ru.rikgela.russianmagic.util.helpers.KeyboardHelper
import java.util.*
import java.util.function.Consumer

class RMMarbleFurnaceBlock(properties: Properties) : AbstractRMFurnace(properties) {

    override fun createTileEntity(state: BlockState, world: IBlockReader): TileEntity {
        return RMTileEntityTypes.RM_MARBLE_FURNACE.get().create()!!
    }

    override fun onBlockPlacedBy(worldIn: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, stack: ItemStack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack)
        if (stack.hasDisplayName()) {
            val tile = worldIn.getTileEntity(pos)
            if (tile is RMMarbleFurnaceTileEntity) {
                tile.customName = stack.displayName
            }
        }
    }

    override fun onReplaced(state: BlockState, worldIn: World, pos: BlockPos, newState: BlockState, isMoving: Boolean) {
        val tile = worldIn.getTileEntity(pos)
        if (tile is RMMarbleFurnaceTileEntity && state.block !== newState.block) {
            (tile.inventory).toNonNullList().forEach(Consumer { item: ItemStack ->
                val itemEntity = ItemEntity(worldIn, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), item)
                worldIn.addEntity(itemEntity)
            })
        }
        if (state.hasTileEntity() && state.block !== newState.block) {
            worldIn.removeTileEntity(pos)
        }
    }

}