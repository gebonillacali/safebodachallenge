package com.gustavobonilla.safebodachallenge.presentation.adapter.recyclerviewadapter

import android.support.v7.widget.RecyclerView
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener

abstract class ItemAdapter<T>:  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Represents the kind of [RecyclerView.Adapter] notifying when the data changes.
     */
    enum class AdapterNotification {
        ALL_DATA,
        RANGE_INSERTED
    }

    /**
     * Adds the items into the list for the adapter.
     *
     * @param items the items to be added to the list for the adapter.
     */
    abstract fun addItems(items: List<T>, adapterNotification: AdapterNotification)

    /**
     * Clears all items in the list of adapter.
     */
    abstract fun clear()

    companion object {
        fun <T> create(clickListener: ClickListener<T>): ItemAdapterImpl<T> {
            return ItemAdapterImpl(clickListener)
        }
    }
}