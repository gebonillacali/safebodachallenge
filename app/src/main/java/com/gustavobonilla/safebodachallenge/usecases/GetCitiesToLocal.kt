package com.gustavobonilla.safebodachallenge.usecases

import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import io.reactivex.Observable

/**
 * Gets the cities from the API and store them into the local db persistence.
 */
class GetCitiesToLocal(override val repository: SafeBodaRepository): UseCase<Int, Int>() {
    override fun createUseCase(parameters: Int): Observable<Int> {
        return repository.updateCities(parameters)
    }
}