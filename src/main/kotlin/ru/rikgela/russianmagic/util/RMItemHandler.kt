package ru.rikgela.russianmagic.util

import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.util.NonNullList
import net.minecraftforge.items.ItemStackHandler

class RMItemHandler(val size: Int, vararg stacks: ItemStack) : ItemStackHandler(size) {
    fun clear() {
        for (index in 0 until this.slots) {
            stacks[index] = ItemStack.EMPTY
            onContentsChanged(index)
        }
    }

    val isEmpty: Boolean
        get() {
            for (stack in stacks) {
                if (stack.isEmpty || stack.item === Items.AIR) {
                    return true
                }
            }
            return false
        }

    fun decrStackSize(index: Int, count: Int): ItemStack {
        val stack = getStackInSlot(index)
        stack.shrink(count)
        onContentsChanged(index)
        return stack
    }

    fun removeStackFromSlot(index: Int) {
        stacks[index] = ItemStack.EMPTY
        onContentsChanged(index)
    }

    fun toNonNullList(): NonNullList<ItemStack> {
        val items = NonNullList.create<ItemStack>()
        for (stack in stacks) {
            items.add(stack)
        }
        return items
    }

    fun setNonNullList(items: NonNullList<ItemStack?>) {
        if (items.size == 0) return
        if (items.size != this.slots) throw IndexOutOfBoundsException("NonNullList must be same size as ItemStackHandler!")
        for (index in items.indices) {
            stacks[index] = items[index]
        }
    }

    fun set(index: Int, items: ItemStack?) {
        stacks[index] = items
    }

    override fun toString(): String {
        return stacks.toString()
    }

    fun get(index: Int): ItemStack {
        return this.stacks[index]
    }

    fun getList(): NonNullList<ItemStack> {
        return this.stacks
    }

    fun getAndRemove(stacks: MutableList<ItemStack>, index: Int): ItemStack {
        return if (index >= 0 && index < stacks.size) stacks.set(index, ItemStack.EMPTY) else ItemStack.EMPTY
    }

    init {
        for (index in stacks.indices) {
            this.stacks[index] = stacks[index]
        }
    }
}