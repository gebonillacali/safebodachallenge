package com.gustavobonilla.safebodachallenge.presentation.sections.flightresult.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.gustavobonilla.safebodachallenge.domain.model.FlightSchedule
import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModel
import com.gustavobonilla.safebodachallenge.presentation.sections.flightresult.view.FlightResultFragment
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.FlightScheduleViewModel
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SafeBodaViewModelFactory
import com.gustavobonilla.safebodachallenge.usecases.FlightSchedulesRequest
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [FlightResultModule::class])
interface FlightResultComponent {
    fun inject(view: FlightResultFragment)
}

@Module
class FlightResultModule(private val view: FragmentActivity) {
    @Provides
    fun providesFlightScheduleViewModel(viewModelFactory: SafeBodaViewModelFactory): BaseViewModel<@JvmSuppressWildcards List<FlightSchedule>,
            FlightSchedulesRequest> {
        return ViewModelProviders.of(view, viewModelFactory).get(FlightScheduleViewModel::class.java)
    }
}