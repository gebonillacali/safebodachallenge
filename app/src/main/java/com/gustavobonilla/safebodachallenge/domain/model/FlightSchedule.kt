package com.gustavobonilla.safebodachallenge.domain.model

/**
 * Represent a complete [FlightSchedule] understood as a Trip itself.
 */
data class FlightSchedule(
        val totalDuration: String,
        val flights: List<FlightData>)

/**
 * Represents a Flight that belongs in a [FlightSchedule].
 */
data class FlightData(
        val flightNumber: Int,
        val airlineId: String,
        val departureAirport: String,
        val departureLocalTime: String,
        val departureTerminal: String,
        val arrivalAirport: String,
        val arrivalLocalTime: String,
        val arrivalTerminal: String)