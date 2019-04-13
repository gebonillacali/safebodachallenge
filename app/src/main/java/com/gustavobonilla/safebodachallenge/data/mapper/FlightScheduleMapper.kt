package com.gustavobonilla.safebodachallenge.data.mapper

import com.gustavobonilla.safebodachallenge.data.entity.Schedule
import com.gustavobonilla.safebodachallenge.domain.model.FlightData
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.getAttribute

/**
 * Maps the data from the Api with the models in domain layer and vice versa.
 */
object FlightScheduleMapper {

    /**
     * Transforms the API [Schedule] model to the DB [FlightSchedule].
     *
     * @param schedule the [Schedule] object from API.
     *
     * @return the [FlightSchedule] object transformed from the [Schedule].
     */
    fun transform(schedule: Schedule): FlightSchedule {
        val flights = schedule.flights
        val flightsData = flights.map {
            FlightData(
                    it.marketingCarrier.flightNumber,
                    it.marketingCarrier.airlineID,
                    it.departure.airportCode,
                    it.departure.scheduledTimeLocal.dateTime,
                    it.departure.terminal.getAttribute({
                        it.departure.terminal.name
                    }, "1") ,
                    it.arrival.airportCode,
                    it.arrival.scheduledTimeLocal.dateTime,
                    it.arrival.terminal.getAttribute({
                        it.arrival.terminal.name
                    }, "1"))
        }
        return FlightSchedule(schedule.totalJourney.duration, flightsData)
    }
}