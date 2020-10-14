package ru.rikgela.russianmagic.objects.entity.projectile

import net.minecraft.entity.EntityType
import net.minecraft.entity.IRendersAsItem
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.DamagingProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.datasync.DataParameter
import net.minecraft.network.datasync.DataSerializers
import net.minecraft.network.datasync.EntityDataManager
import net.minecraft.util.Util
import net.minecraft.world.World
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import ru.rikgela.russianmagic.objects.entity.projectile.AbstractProjectileEntity

@OnlyIn(value = Dist.CLIENT, _interface = IRendersAsItem::class)
abstract class AbstractProjectileEntity : SpellProjectileEntity, IRendersAsItem {
    constructor(p_i50166_1_: EntityType<out AbstractProjectileEntity>, p_i50166_2_: World) : super(p_i50166_1_, p_i50166_2_) {}
    constructor(p_i50167_1_: EntityType<out AbstractProjectileEntity>, p_i50167_2_: Double, p_i50167_4_: Double, p_i50167_6_: Double, p_i50167_8_: Double, p_i50167_10_: Double, p_i50167_12_: Double, p_i50167_14_: World) : super(p_i50167_1_, p_i50167_2_, p_i50167_4_, p_i50167_6_, p_i50167_8_, p_i50167_10_, p_i50167_12_, p_i50167_14_) {}
    constructor(p_i50168_1_: EntityType<out AbstractProjectileEntity>, p_i50168_2_: LivingEntity, p_i50168_3_: Double, p_i50168_5_: Double, p_i50168_7_: Double, p_i50168_9_: World) : super(p_i50168_1_, p_i50168_2_, p_i50168_3_, p_i50168_5_, p_i50168_7_, p_i50168_9_) {}

    protected var stack: ItemStack
        protected get() = getDataManager().get(STACK) as ItemStack
        set(p_213898_1_) {
            if (p_213898_1_.item !== Items.FIRE_CHARGE || p_213898_1_.hasTag()) {
                getDataManager().set(STACK, Util.make(p_213898_1_.copy(), { p_213897_0_: ItemStack -> p_213897_0_.count = 1 }))
            }
        }

    @OnlyIn(Dist.CLIENT)
    override fun getItem(): ItemStack {
        val itemstack = stack
        return if (itemstack.isEmpty) ItemStack(Items.FIRE_CHARGE) else itemstack
    }

    override fun registerData() {
        getDataManager().register(STACK, ItemStack.EMPTY)
    }

    override fun writeAdditional(compound: CompoundNBT) {
        super.writeAdditional(compound)
        val itemstack = stack
        if (!itemstack.isEmpty) {
            compound.put("Item", itemstack.write(CompoundNBT()))
        }
    }

    override fun readAdditional(compound: CompoundNBT) {
        super.readAdditional(compound)
        val itemstack = ItemStack.read(compound.getCompound("Item"))
        stack = itemstack
    }

    companion object {
        private var STACK: DataParameter<ItemStack>? = null

        init {
            STACK = EntityDataManager.createKey(AbstractProjectileEntity::class.java, DataSerializers.ITEMSTACK)
        }
    }
}