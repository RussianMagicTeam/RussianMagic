package ru.rikgela.russianmagic

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import ru.rikgela.russianmagic.init.RMItems
import java.util.function.Supplier

class ItemGroup(name: String, private val iconSupplier: Supplier<ItemStack?>) : ItemGroup(name) {
    override fun createIcon(): ItemStack? {
        return iconSupplier.get()
    }
}

object ItemGroups {
    val RUSSIAN_MAGIC_ITEM_GROUP: ItemGroup = ru.rikgela.russianmagic.ItemGroup("russian_magic_items", Supplier { ItemStack(RMItems.STONE_MAGIC_OBJECT.get()) })
}
