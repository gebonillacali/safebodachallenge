package com.gustavobonilla.safebodachallenge.data.typeadapter

import com.google.gson.GsonBuilder
import com.gustavobonilla.safebodachallenge.ModelCreator
import com.gustavobonilla.safebodachallenge.data.entity.Airports
import com.gustavobonilla.safebodachallenge.fromJson
import org.junit.Assert.assertEquals
import org.junit.Test

class AirportTypeAdapterTest {

    @Test
    fun deserialize() {
        val gson = GsonBuilder()
                .registerTypeAdapter(Airports::class.java, AirportTypeAdapter())
                .create()
        val airports = gson.fromJson<Airports>(ModelCreator.airportResponse)
        assertEquals(airports.airportCodes.first(), FIRST_AIRPORT)
        assertEquals(airports.airportCodes.last(), LAST_AIRPORT)
    }

    companion object {
        const val FIRST_AIRPORT = "SXF"
        const val LAST_AIRPORT = "TXL"
    }
}