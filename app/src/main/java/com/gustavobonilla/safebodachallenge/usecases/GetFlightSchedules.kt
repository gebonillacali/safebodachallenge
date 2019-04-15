package com.gustavobonilla.safebodachallenge.usecases

import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import io.reactivex.Observable

/**
 * Gets the Flight schedules regarding the origin, destination airport and date.
 */
class GetFlightSchedules(override val repository: SafeBodaRepository): UseCase<List<FlightSchedule>, FlightSchedulesRequest>() {

    override val requiresAuthToken: Boolean = true

    override fun createUseCase(parameters: FlightSchedulesRequest): Observable<List<FlightSchedule>> {
        return repository.getFlightSchedule(parameters.originAirport, parameters.destinationAirport, parameters.dateTime)
    }
}

/**
 * Represents the parameters for request the Flight Schedules
 */
data class FlightSchedulesRequest(
        val originAirport: String,
        val destinationAirport: String,
        val dateTime: String)
