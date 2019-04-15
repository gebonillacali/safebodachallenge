package com.gustavobonilla.safebodachallenge.usecases

import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import io.reactivex.Observable

/**
 * Gets cities regarding the values written in string that match with the name of a city.
 */
class GetCitiesByName(override val repository: SafeBodaRepository): UseCase<List<City>, String>() {

    override val requiresAuthToken: Boolean = false

    override fun createUseCase(parameters: String): Observable<List<City>> {
        return repository.getCities("%$parameters%")
    }
}