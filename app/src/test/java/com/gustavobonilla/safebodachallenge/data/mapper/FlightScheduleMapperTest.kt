package com.gustavobonilla.safebodachallenge.data.mapper

import com.gustavobonilla.safebodachallenge.ModelCreator
import org.junit.Assert.assertEquals
import org.junit.Test

class FlightScheduleMapperTest {

    @Test
    fun transform() {
        val schedule = ModelCreator.schedule
        val flightSchedule = FlightScheduleMapper.transform(schedule)
        assertEquals(schedule.flights.first().arrival.airportCode, flightSchedule.flights.first().arrivalAirport)
        assertEquals(schedule.flights.first().departure.airportCode, flightSchedule.flights.first().departureAirport)
        assertEquals(schedule.totalJourney.duration, flightSchedule.totalDuration)
    }
}