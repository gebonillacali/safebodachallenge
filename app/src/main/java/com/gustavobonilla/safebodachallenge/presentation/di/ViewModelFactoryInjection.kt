package com.gustavobonilla.safebodachallenge.presentation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.SafeBodaViewModelFactory
import com.gustavobonilla.safebodachallenge.presentation.viewmodel.StoreCitiesViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.CLASS)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(safeBodaViewModelFactory: SafeBodaViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(StoreCitiesViewModel::class)
    internal abstract fun citySelectionViewModel(viewModel: StoreCitiesViewModel): ViewModel
}
