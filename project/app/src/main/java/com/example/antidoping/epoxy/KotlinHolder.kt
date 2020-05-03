package com.example.antidoping.epoxy

import android.content.Intent
import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.example.antidoping.DetailActivity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class KotlinHolder : EpoxyHolder() {

    private lateinit var view: View


    override fun bindView(itemView: View) {
        view = itemView
    }

    protected fun <V : View> bind(id: Int): ReadOnlyProperty<KotlinHolder, V> =
            Lazy { holder: KotlinHolder, prop ->
                holder.view.findViewById(id) as V?
                        ?: throw IllegalStateException("View ID $id for '${prop.name}' not found.")
            }


    /**
     * Taken from Kotterknife.
     * https://github.com/JakeWharton/kotterknife
     */
    private class Lazy<V>(private val initializer: (KotlinHolder, KProperty<*>) -> V)
        : ReadOnlyProperty<KotlinHolder, V> {

        private object EMPTY

        private var value: Any? = EMPTY

        override fun getValue(thisRef: KotlinHolder, property: KProperty<*>): V {
            if (value == EMPTY) {
                value = initializer(thisRef, property)
            }
            @Suppress("UNCHECKED_CAST")
            return value as V
        }
    }
}
