package com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener

class CityViewHolder(private val view: View): BaseViewHolder<City>(view) {
    private val cityName = view.findViewById<TextView>(R.id.cityName)
    private val airportCode = view.findViewById<TextView>(R.id.airportCode)

    //region [SafeBodaAppViewHolder] Impl
    override fun bindItem(item: City, clickListener: ClickListener<City>) {
        itemView.setOnClickListener {
            clickListener.onItemClickListener(item)
        }
        cityName.text = item.cityName
        airportCode.text = item.airportCode
    }
    //endregion
}