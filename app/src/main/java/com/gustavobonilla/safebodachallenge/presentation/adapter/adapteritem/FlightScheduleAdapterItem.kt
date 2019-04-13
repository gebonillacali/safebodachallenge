package com.gustavobonilla.safebodachallenge.presentation.adapter.adapteritem

import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener
import com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder.BaseViewHolder

/**
 * Represents an [AdapterItem] in the [RecyclerView] for a [FlightSchedule].
 */
class FlightScheduleAdapterItem(private val item: FlightSchedule, private val clickListener: ClickListener<FlightSchedule>): BaseAdapterItem<FlightSchedule> {
    override val layoutRes: Int
        get() = R.layout.flight_list_item

    override fun bindData(viewHolder: BaseViewHolder<FlightSchedule>) {
        viewHolder.bindItem(item, clickListener)
    }

    override fun getSection(): String = ""

    override fun equals(other: Any?): Boolean {
        return other is FlightScheduleAdapterItem && item.flights == other.item.flights
    }

    override fun hashCode(): Int {
        return super.hashCode() +
                item.totalDuration.hashCode() +
                item.flights.last().airlineId.hashCode() +
                item.flights.first().airlineId.hashCode() +
                item.flights.first().arrivalLocalTime.hashCode() +
                item.flights.last().arrivalLocalTime.hashCode();
    }
}