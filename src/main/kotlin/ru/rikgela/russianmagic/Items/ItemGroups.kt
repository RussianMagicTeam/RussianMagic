package ru.rikgela.russianmagic.Items

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import java.util.function.Supplier

class ItemGroup(name: String, private val iconSupplier: Supplier<ItemStack?>?) : ItemGroup(name) {
    override fun createIcon(): ItemStack? {
        if (iconSupplier != null) {
            return iconSupplier.get()
        }
        return null
    }
}

object ItemGroups {
    val RUSSIAN_MAGIC_ITEM_GROUP: ItemGroup = ru.rikgela.russianmagic.Items.ItemGroup("russian_magic_items", Supplier { ItemStack(Items.STONE_MAGIC_OBJECT.get()) })
}
