package com.gustavobonilla.safebodachallenge.usecases

import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import io.reactivex.Observable

/**
 * Gets a city by the Airport code given.
 */
class GetCityByAirportCode(override val repository: SafeBodaRepository): UseCase<City, String>() {

    override val requiresAuthToken: Boolean = false

    override fun createUseCase(parameters: String): Observable<City> {
        return repository.getCityByAirportCode("%$parameters%")
    }
}