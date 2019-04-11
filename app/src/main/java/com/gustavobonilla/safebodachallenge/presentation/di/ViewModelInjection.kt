package com.gustavobonilla.safebodachallenge.presentation.di

import android.arch.lifecycle.ViewModel
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.FlightScheduleViewModel
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SearchCitiesViewModel
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.StoreCitiesViewModel
import com.gustavobonilla.safebodachallenge.usecases.FlightSchedulesRequest
import com.gustavobonilla.safebodachallenge.usecases.UseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun providesStoreCitiesViewModel(useCase: UseCase<Int, Int>): ViewModel {
        return StoreCitiesViewModel(useCase)
    }

    @Provides
    @Singleton
    fun providesSearchCitiesViewModel(useCase: UseCase<List<City>, String>): ViewModel {
        return SearchCitiesViewModel(useCase)
    }

    @Provides
    @Singleton
    fun providesFlightScheduleViewModel(useCase: UseCase<List<FlightSchedule>, FlightSchedulesRequest>): ViewModel {
        return FlightScheduleViewModel(useCase)
    }
}