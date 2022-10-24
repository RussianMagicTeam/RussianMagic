package ru.rikgela.russianmagic.init

import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import java.util.function.Supplier

class RMCreativeModeTab(name: String, private val iconSupplier: Supplier<ItemStack?>) : CreativeModeTab(name) {
    override fun makeIcon(): ItemStack? {
        return iconSupplier.get()
    }
}

object RMCreativeModeTabs {
    val RUSSIAN_MAGIC_ITEM_GROUP: RMCreativeModeTab = RMCreativeModeTab("russian_magic_items") {
        ItemStack(RMItems.STONE_MAGIC_OBJECT.get())
    }
}
