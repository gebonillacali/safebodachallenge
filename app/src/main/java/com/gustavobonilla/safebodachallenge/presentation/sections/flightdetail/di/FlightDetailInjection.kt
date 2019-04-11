package com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.view.DetailCityDataFragment
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModel
import com.gustavobonilla.safebodachallenge.presentation.sections.flightdetail.view.FlightDetailFragment
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.CityViewModel
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SafeBodaViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [FlightDetailModule::class])
interface FlightDetailComponent {
    fun inject(view: FlightDetailFragment)
    fun injectInDetail(view: DetailCityDataFragment)
}

@Module
class FlightDetailModule(private val view: FragmentActivity) {
    @Provides
    fun providesCityViewModel(viewModelFactory: SafeBodaViewModelFactory): BaseViewModel<City, String> {
        return ViewModelProviders.of(view, viewModelFactory).get(CityViewModel::class.java)
    }
}