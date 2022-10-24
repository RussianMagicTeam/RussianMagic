package ru.rikgela.russianmagic.util

import net.minecraft.util.IntReferenceHolder
import java.util.function.IntConsumer
import java.util.function.IntSupplier

class FunctionalIntReferenceHolder(private val getter: IntSupplier, private val setter: IntConsumer) : IntReferenceHolder() {
    override fun get(): Int {
        return getter.asInt
    }

    override fun set(value: Int) {
        setter.accept(value)
    }

}