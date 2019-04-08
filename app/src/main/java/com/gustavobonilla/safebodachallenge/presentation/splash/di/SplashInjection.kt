package com.gustavobonilla.safebodachallenge.presentation.splash.di

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.gustavobonilla.safebodachallenge.presentation.base.BaseViewModel
import com.gustavobonilla.safebodachallenge.presentation.splash.view.SplashActivity
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SafeBodaViewModelFactory
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.StoreCitiesViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    fun inject(view: SplashActivity)
}

@Module
class SplashModule(private val view: FragmentActivity) {
    @Provides
    fun providesStoreCitiesViewModel(viewModelFactory: SafeBodaViewModelFactory): BaseViewModel<Int, Int> {
        return ViewModelProviders.of(view, viewModelFactory).get(StoreCitiesViewModel::class.java)
    }
}