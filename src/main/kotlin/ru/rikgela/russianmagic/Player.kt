/*package ru.rikgela.russianmagic

import com.sun.javafx.media.PrismMediaFrameHandler.getHandler
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.ItemGroup.MATERIALS
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.Capability.IStorage
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import javax.annotation.Nonnull


interface IManaHandler {
    var mana: Int

    fun addMana(mana: Int)
    fun removeMana(mana: Int)
}

@CapabilityInject(IManaHandler::class)
val CAPABILITY_MANA: Capability<IManaHandler>? = null

class DefaultManaHandler : IManaHandler {
    override var mana = 0

    override fun addMana(mana: Int) {
        this.mana += mana
    }

    override fun removeMana(mana: Int) {
        this.mana -= mana
        if (this.mana < 0) this.mana = 0
    }
}



class Storage : IStorage<IManaHandler> {
    override fun writeNBT(capability: Capability<IManaHandler>, instance: IManaHandler, side: EnumFacing): NBTBase {
        val tag = NBTTagCompound()
        tag.setInteger("mana", instance.mana)
        return tag
    }

    override fun readNBT(capability: Capability<IManaHandler?>?, instance: IManaHandler, side: EnumFacing?, nbt: NBTBase) {
        val tag: NBTTagCompound = nbt as NBTTagCompound
        instance.mana = tag.getInteger("mana")
    }
}

public class Provider implements ICapabilitySerializable<NBTTagCompound> {

        IManaHandler instance = CAPABILITY_MANA.getDefaultInstance();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {

            return capability == CAPABILITY_MANA;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {

            return hasCapability(capability, facing) ? CAPABILITY_MANA.<T>cast(instance) : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {

            return (NBTTagCompound) CAPABILITY_MANA.getStorage().writeNBT(CAPABILITY_MANA, instance, null);
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {

            CAPABILITY_MANA.getStorage().readNBT(CAPABILITY_MANA, instance, null, nbt);
        }
    }

CapabilityManager.INSTANCE.register(IManaHandler.class, new Storage(), DefaultManaHandler.class);

@SubscribeEvent
fun attachCapabilities(event: AttachCapabilitiesEvent<Entity?>) {
    if (event.getObject() is EntityPlayer) event.addCapability(ResourceLocation("MODID", "NAME"), Provider())
}

@SubscribeEvent
fun clonePlayer(event: Clone) {
    val original: IManaHandler = getHandler(event.getOriginal())
    val clone: IManaHandler = getHandler(event.getEntity())
    clone.mana = original.mana
}

fun getHandler(entity: Entity): IManaHandler? {
    return if (entity.hasCapability(CAPABILITY_MANA, EnumFacing.DOWN)) entity.getCapability(CAPABILITY_MANA, EnumFacing.DOWN) else null
}





fun breakOtherBlock(player: EntityPlayer, stack: ItemStack?, pos: BlockPos?, originPos: BlockPos?, side: EnumFacing) {
    if (stack != null) return
    val world: World = player.worldObj
    val mat: Material = world.getBlockState(pos).material
    if (!ToolCommons.isRightMaterial(mat, MATERIALS)) return
    if (world.isAirBlock(pos)) return
    val fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItemMainhand())
    val silk = EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) > 0
    val doX = side.getFrontOffsetX() === 0
    val doY = side.getFrontOffsetY() === 0
    val doZ = side.getFrontOffsetZ() === 0
    val origLevel: Int = getLevel(stack)
    var level = origLevel
    if (ItemTemperanceStone.hasTemperanceActive(player) && level > 2) level = 2
    val range = level - 1
    val rangeY = Math.max(1, range)
    if (range == 0 && level != 1) return
    val beginDiff = BlockPos(if (doX) -range else 0, if (doY) -1 else 0, if (doZ) -range else 0)
    val endDiff = BlockPos(if (doX) range else 0, if (doY) rangeY * 2 - 1 else 0, if (doZ) range else 0)
    ToolCommons.removeBlocksInIteration(player, stack, world, pos, beginDiff, endDiff, null, MATERIALS, silk, fortune, isTipped(stack))
    if (origLevel == 5) player.addStat(ModAchievements.rankSSPick, 1)
}




fun onBlockDestroyed(@Nonnull stack: ItemStack?, @Nonnull world: World?, state: IBlockState, @Nonnull pos: BlockPos?, @Nonnull entity: EntityLivingBase?): Boolean {
    if (state.getBlockHardness(world, pos) !== 0f) ToolCommons.damageItem(stack, 1, entity, getManaPerDmg())
    return true
}

@Nonnull
fun onItemUse(stack: ItemStack?, player: EntityPlayer, world: World?, pos: BlockPos?, hand: EnumHand?, side: EnumFacing?, sx: Float, sy: Float, sz: Float): EnumActionResult? {
    for (i in 0 until player.inventory.getSizeInventory()) {
        val stackAt: ItemStack = player.inventory.getStackInSlot(i)
        if (stackAt != null && TORCH_PATTERN.matcher(stackAt.item.getUnlocalizedName()).find()) {
            val did: EnumActionResult = stackAt.item.onItemUse(stackAt, player, world, pos, hand, side, sx, sy, sz)
            if (stackAt.stackSize === 0) player.inventory.setInventorySlotContents(i, null)
            ItemsRemainingRenderHandler.set(player, ItemStack(Blocks.TORCH), TORCH_PATTERN)
            return did
        }
    }
    return EnumActionResult.PASS
}

fun getManaPerDmg(): Int {
    return MANA_PER_DAMAGE
}*/