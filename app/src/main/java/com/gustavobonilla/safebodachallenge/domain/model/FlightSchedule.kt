package com.gustavobonilla.safebodachallenge.domain.model

data class FlightSchedule(
        val totalDuration: String,
        val flights: List<FlightData>)

data class FlightData(
        val flightNumber: Int,
        val airlineId: String,
        val departureAirport: String,
        val departureLocalTime: String,
        val departureTerminal: String,
        val arrivalAirport: String,
        val arrivalLocalTime: String,
        val arrivalTerminal: String)