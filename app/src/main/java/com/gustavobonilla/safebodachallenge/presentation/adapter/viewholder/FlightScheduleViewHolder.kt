package com.gustavobonilla.safebodachallenge.presentation.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.gustavobonilla.safebodachallenge.R
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.adapter.ClickListener
import java.text.SimpleDateFormat
import java.util.*

/**
 * The [RecyclerView.ViewHolder] for the [FlightSchedule] model.
 */
class FlightScheduleViewHolder(view: View): BaseViewHolder<FlightSchedule>(view) {

    private val originAirport  = view.findViewById<TextView>(R.id.originAirport)
    private val originTerminalAirport  = view.findViewById<TextView>(R.id.originTerminalAirport)
    private val departureTime = view.findViewById<TextView>(R.id.departureTime)
    private val departureDate = view.findViewById<TextView>(R.id.departureDate)
    private val destinationAirport = view.findViewById<TextView>(R.id.destinationAirport)
    private val destinationTerminalAirport = view.findViewById<TextView>(R.id.destinationTerminalAirport)
    private val arrivalTime = view.findViewById<TextView>(R.id.arrivalTime)
    private val arrivalDate = view.findViewById<TextView>(R.id.arrivalDate)
    private val flightInfo = view.findViewById<TextView>(R.id.flightInfo)

    override fun bindItem(item: FlightSchedule, clickListener: ClickListener<FlightSchedule>) {
        val firstFlight = item.flights.first()
        val lastFlight = item.flights.last()
        originAirport.text = firstFlight.departureAirport
        originTerminalAirport.text = "Terminal ${firstFlight.departureTerminal}"
        departureTime.text = firstFlight.departureLocalTime.split("T")[1]
        departureDate.text = getDay(firstFlight.departureLocalTime)

        destinationAirport.text = lastFlight.arrivalAirport
        destinationTerminalAirport.text = "Terminal ${lastFlight.arrivalTerminal}"
        arrivalTime.text = lastFlight.arrivalLocalTime.split("T")[1]
        arrivalDate.text = getDay(lastFlight.arrivalLocalTime)

        val formattedDuration = item.totalDuration.replace("P","").replace("T", "")
        flightInfo.text = "${item.flights.size - 1} Stops | #Flight ${item.flights[0].flightNumber} | $formattedDuration"

        itemView.setOnClickListener {
            clickListener.onItemClickListener(item)
        }
    }

    /**
     * Gets the day formatted, Ex. tue. 10 may 2019.
     *
     * @param fullDate the date of a [FlightSchedule].
     *
     * @return the date formatted. Ex. tue. 10 may 2019.
     */
    private fun getDay(fullDate: String): String {
        val dateParse = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(fullDate.split("T")[0])
        return SimpleDateFormat("EE. dd MMM yyyy", Locale.ENGLISH).format(dateParse).toLowerCase()
    }
}