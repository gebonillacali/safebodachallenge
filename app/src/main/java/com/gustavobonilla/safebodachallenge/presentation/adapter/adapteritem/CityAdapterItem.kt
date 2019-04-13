package com.gustavobonilla.safebodachallenge.presentation.adapter.adapteritem

import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.BaseViewHolder

/**
 * Represents an [AdapterItem] in the [RecyclerView] for a [City].
 */
class CityAdapterItem(private val item: City, private val clickListener: ClickListener<City>): BaseAdapterItem<City> {
    override val layoutRes: Int
        get() = R.layout.city_list_item

    override fun bindData(viewHolder: BaseViewHolder<City>) {
        viewHolder.bindItem(item, clickListener)
    }

    override fun getSection(): String = ""

    override fun equals(other: Any?): Boolean {
        return other is CityAdapterItem && item.airportCode == other.item.airportCode && item.cityName == other.item.cityName
    }

    override fun hashCode(): Int {
        return super.hashCode() + item.cityCode.hashCode() + item.airportCode.hashCode() + item.cityName.hashCode()
    }
}