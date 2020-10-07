package ru.rikgela.russianmagic

/*
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.util.math.vector.Vector3i
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.common.ForgeHooks
import vazkii.botania.api.BotaniaAPI
import vazkii.botania.api.mana.ManaItemHandler
import vazkii.botania.common.core.handler.ConfigHandler
import vazkii.botania.common.item.ModItems
import vazkii.botania.common.item.equipment.tool.elementium.ItemElementiumPick
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick
import java.util.*
import java.util.function.Predicate


object ToolCommons {
    val materialsPick = Arrays.asList(Material.ROCK, Material.IRON, Material.ICE, Material.GLASS, Material.PISTON, Material.ANVIL, Material.SHULKER)
    val materialsShovel = Arrays.asList(Material.ORGANIC, Material.EARTH, Material.SAND, Material.SNOW, Material.SNOW_BLOCK, Material.CLAY)
    val materialsAxe = Arrays.asList(Material.CORAL, Material.LEAVES, Material.PLANTS, Material.WOOD, Material.GOURD)

    /**
     * Consumes as much mana as possible, returning the amount of damage that couldn't be paid with mana
     */
    fun damageItemIfPossible(stack: ItemStack?, amount: Int, entity: LivingEntity?, manaPerDamage: Int): Int {
        var amount = amount
        if (entity !is PlayerEntity) {
            return amount
        }
        while (amount > 0) {
            if (ManaItemHandler.instance().requestManaExactForTool(stack, entity, manaPerDamage, true)) {
                amount--
            } else {
                break
            }
        }
        return amount
    }

    fun removeBlocksInIteration(player: PlayerEntity, stack: ItemStack, world: World, centerPos: BlockPos,
                                startDelta: Vector3i?, endDelta: Vector3i?, filter: Predicate<BlockState?>,
                                dispose: Boolean) {
        for (iterPos in BlockPos.getAllInBoxMutable(centerPos.add(startDelta),
                centerPos.add(endDelta))) {
            // skip original block space to avoid crash, vanilla code in the tool class will handle it
            if (iterPos == centerPos) {
                continue
            }
            removeBlockWithDrops(player, stack, world, iterPos, filter, dispose)
        }
    }

    @JvmOverloads
    fun removeBlockWithDrops(player: PlayerEntity, stack: ItemStack, world: World, pos: BlockPos?,
                             filter: Predicate<BlockState?>,
                             dispose: Boolean, particles: Boolean = true) {
        if (!world.isBlockLoaded(pos)) {
            return
        }
        val state = world.getBlockState(pos)
        val block = state.block
        if (!world.isRemote && filter.test(state)
                && !block.isAir(state, world, pos) && state.getPlayerRelativeBlockHardness(player, world, pos) > 0 && state.canHarvestBlock(player.world, pos, player)) {
            val exp = ForgeHooks.onBlockBreakEvent(world, (player as ServerPlayerEntity).interactionManager.gameType, player, pos)
            if (exp == -1) {
                return
            }
            if (!player.abilities.isCreativeMode) {
                val tile = world.getTileEntity(pos)
                if (block.removedByPlayer(state, world, pos, player, true, world.getFluidState(pos))) {
                    block.onPlayerDestroy(world, pos, state)
                    if (!dispose || !ItemElementiumPick.isDisposable(block)) {
                        block.harvestBlock(world, player, pos, state, tile, stack)
                        block.dropXpOnBlockBreak(world as ServerWorld, pos, exp)
                    }
                }
                val paidWithMana: Boolean = ManaItemHandler.instance().requestManaExactForTool(stack, player, 80, true)
                if (!paidWithMana) {
                    stack.damageItem<PlayerEntity>(1, player, { e: PlayerEntity? -> })
                }
            } else {
                world.removeBlock(pos, false)
            }
            if (particles && ConfigHandler.COMMON.blockBreakParticles.get() && ConfigHandler.COMMON.blockBreakParticlesTool.get()) {
                world.playEvent(2001, pos, Block.getStateId(state))
            }
        }
    }

    fun getToolPriority(stack: ItemStack): Int {
        if (stack.isEmpty) {
            return 0
        }
        val item = stack.item as? ToolItem ?: return 0
        val material = item.tier
        var materialLevel = 0
        if (material === BotaniaAPI.instance().getManasteelItemTier()) {
            materialLevel = 10
        }
        if (material === BotaniaAPI.instance().getElementiumItemTier()) {
            materialLevel = 11
        }
        if (material === BotaniaAPI.instance().getTerrasteelItemTier()) {
            materialLevel = 20
        }
        var modifier = 0
        if (item === ModItems.terraPick) {
            modifier = ItemTerraPick.getLevel(stack)
        }
        val efficiency = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, stack)
        return materialLevel * 100 + modifier * 10 + efficiency
    }

    fun raytraceFromEntity(e: Entity, distance: Double, fluids: Boolean): BlockRayTraceResult {
        return e.pick(distance, 1f, fluids) as BlockRayTraceResult
    }
}*/