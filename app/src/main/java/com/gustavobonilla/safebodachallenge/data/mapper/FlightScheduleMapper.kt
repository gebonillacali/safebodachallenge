package com.gustavobonilla.safebodachallenge.data.mapper

import com.gustavobonilla.safebodachallenge.data.entity.Schedule
import com.gustavobonilla.safebodachallenge.domain.model.FlightData
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule

object FlightScheduleMapper {
    fun transform(schedule: Schedule): FlightSchedule {
        val flights = schedule.flights
        val flightsData = flights.map {
            FlightData(
                    it.marketingCarrier.flightNumber,
                    it.marketingCarrier.airlineID,
                    it.departure.airportCode,
                    it.departure.scheduledTimeLocal.dateTime,
                    it.arrival.airportCode,
                    it.arrival.scheduledTimeLocal.dateTime)
        }
        return FlightSchedule(schedule.totalJourney.duration, flightsData)
    }
}