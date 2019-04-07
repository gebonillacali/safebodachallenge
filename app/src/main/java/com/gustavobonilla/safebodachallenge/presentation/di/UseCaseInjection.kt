package com.gustavobonilla.safebodachallenge.presentation.di

import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import com.gustavobonilla.safebodachallenge.usecases.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesGetCitiesToLocal(repository: SafeBodaRepository): UseCase<Int, Int> {
        return GetCitiesToLocal(repository)
    }

    @Provides
    fun providesGetCitiesByName(repository: SafeBodaRepository): UseCase<List<City>, String> {
        return GetCitiesByName(repository)
    }

    @Provides
    fun providesGetCityByAirportCode(repository: SafeBodaRepository): UseCase<City, String> {
        return GetCityByAirportCode(repository)
    }

    @Provides
    fun providesGetFlightSchedules(repository: SafeBodaRepository): UseCase<List<FlightSchedule>, FlightSchedulesRequest> {
        return GetFlightSchedules(repository)
    }
}