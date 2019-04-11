package com.gustavobonilla.safebodachallenge.presentation.sections.home.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.gustavobonilla.safebodachallenge.domain.model.City
import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModel
import com.gustavobonilla.safebodachallenge.presentation.sections.home.view.HomeFragment
import com.gustavobonilla.safebodachallenge.presentation.util.DialogSearchCity
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SafeBodaViewModelFactory
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SearchCitiesViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(view: HomeFragment)
    fun injectDialog(view: DialogSearchCity)
}

@Module
class HomeModule(private val view: FragmentActivity) {

    @Provides
    fun providesSearchCitiesViewModel(viewModelFactory: SafeBodaViewModelFactory): BaseViewModel<@JvmSuppressWildcards List<City>, String> {
        return ViewModelProviders.of(view, viewModelFactory).get(SearchCitiesViewModel::class.java)
    }
}
