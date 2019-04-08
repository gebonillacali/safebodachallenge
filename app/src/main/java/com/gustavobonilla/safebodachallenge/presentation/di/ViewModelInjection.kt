package com.gustavobonilla.safebodachallenge.presentation.di

import android.arch.lifecycle.ViewModel
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.StoreCitiesViewModel
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
}