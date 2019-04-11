package com.gustavobonilla.safebodachallenge.presentation.adapter.recyclerviewadapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.checkType
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.inflate
import com.gustavobonilla.safebodachallenge.isNotNull
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener
import com.gustavobonilla.safebodachallenge.presentation.adapter.adapteritem.BaseAdapterItem
import com.gustavobonilla.safebodachallenge.presentation.adapter.adapteritem.CityAdapterItem
import com.gustavobonilla.safebodachallenge.presentation.adapter.adapteritem.CreateViewHolder
import com.gustavobonilla.safebodachallenge.presentation.adapter.adapteritem.FlightScheduleAdapterItem
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.BaseViewHolder
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.CityViewHolder
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.FlightScheduleViewHolder

/**
 * The [RecyclerView.Adapter] that is in charge of providing the views to be rendered in the [RecyclerView].
 *
 * @param clickListener the [ClickListener] which will be triggered when an item is clicked.
 */
class ItemAdapterImpl<T>(private val clickListener: ClickListener<T>) : ItemAdapter<T>() {

    private val list: MutableList<BaseAdapterItem<T>> = mutableListOf()
    /**
     * Part of Delegate Pattern.
     *
     * If a new kind of view has to be added should follow this:
     *
     * Map element with key a layoutResource:Int the layout for the item to be rendered,
     * and [CreateViewHolder] function definition as value.
     */
    private val viewHolderFactory: MutableMap<Int, CreateViewHolder> = mutableMapOf(
            R.layout.city_list_item to { layout, parent -> CityViewHolder(parent.inflate(layout)) },
            R.layout.flight_list_item to { layout, parent -> FlightScheduleViewHolder(parent.inflate(layout)) }
    )

    //region [RecyclerView.Adapter] implementation
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return viewHolderFactory[viewType]!!.invoke(viewType, parent)
    }

    override fun getItemCount(): Int = list.size

    @JvmSuppressWildcards
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (checkType<BaseViewHolder<T>>(viewHolder)) {
            list[position].bindData(viewHolder as BaseViewHolder<T>)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].layoutRes
    }
    //endregion

    //region [ItemAdapter] Impl
    override fun addItems(items: List<T>, adapterNotification: AdapterNotification) {
        synchronized(list) {
            val adapterItems = items.map {
                when (it) {
                    is City -> CityAdapterItem(it, clickListener as ClickListener<City>)
                    is FlightSchedule -> FlightScheduleAdapterItem(it, clickListener as ClickListener<FlightSchedule>)
                    else -> null
                }
            }.filter {
                it.isNotNull()
            }.map {
                it as BaseAdapterItem<T>
            }.filter { !list.contains(it) }

            when (adapterNotification) {
                AdapterNotification.ALL_DATA -> {
                    list.addAll(adapterItems)
                    notifyDataSetChanged()
                }
                AdapterNotification.RANGE_INSERTED -> {
                    val positionStart = if (list.isNotEmpty()) list.size - 1 else 0
                    list.addAll(adapterItems)
                    notifyItemRangeInserted(positionStart, adapterItems.size - 1)
                }
            }
        }
    }

    override fun clear() {
        synchronized(list) {
            list.clear()
        }
    }

    //endregion

    //region Private Methods
    /**
     * Indicates if is safe to request an item in adapter.
     *
     * @param itemPosition the position to check.
     */
    private fun isSafeBound(itemPosition: Int): Boolean {
        return 0 <= itemPosition && itemPosition < list.size
    }
    //endregion
}