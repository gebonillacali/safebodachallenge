package com.gustavobonilla.safebodachallenge.presentation.adapter.adapteritem

import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.BaseViewHolder

class FlightScheduleAdapterItem(private val item: FlightSchedule, private val clickListener: ClickListener<FlightSchedule>): BaseAdapterItem<FlightSchedule> {
    override val layoutRes: Int
        get() = R.layout.flight_list_item

    override fun bindData(viewHolder: BaseViewHolder<FlightSchedule>) {
        viewHolder.bindItem(item, clickListener)
    }

    override fun getSection(): String = ""
}