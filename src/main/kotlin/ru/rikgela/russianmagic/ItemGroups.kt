package ru.rikgela.russianmagic

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import java.util.function.Supplier

class ModItemGroup(name: String, private val iconSupplier: Supplier<ItemStack?>?) : ItemGroup(name) {
    override fun createIcon(): ItemStack? {
        if (iconSupplier != null) {
            return iconSupplier.get()
        }
        return null
    }
}

object ModItemGroups {
    val MOD_ITEMS_ITEM_GROUP: ItemGroup = ModItemGroup("russian_magic_items", Supplier { ItemStack(ModItems.STONE_STICK.get()) })
}
