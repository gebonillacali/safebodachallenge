package com.gustavobonilla.safebodachallenge.data.typeadapter

import com.google.gson.GsonBuilder
import com.gustavobonilla.safebodachallenge.ModelCreator
import com.gustavobonilla.safebodachallenge.data.entity.Schedule
import com.gustavobonilla.safebodachallenge.fromJson
import org.junit.Test

import org.junit.Assert.assertEquals

class ScheduleTypeAdapterTest {

    @Test
    fun deserialize() {
        val gson = GsonBuilder()
                .registerTypeAdapter(Schedule::class.java, ScheduleTypeAdapter())
                .create()
        val schedule = gson.fromJson<Schedule>(ModelCreator.scheduleJsonResponse)
        assertEquals(schedule.totalJourney.duration, TOTAL_JOURNEY)
        assertEquals(schedule.flights.first().departure.airportCode, FIRST_AIRPORT)
        assertEquals(schedule.flights.last().arrival.airportCode, LAST_AIRPORT)

    }

    companion object {
        const val TOTAL_JOURNEY = "P1DT1H29M"
        const val FIRST_AIRPORT = "SXF"
        const val LAST_AIRPORT = "TXL"
    }
}