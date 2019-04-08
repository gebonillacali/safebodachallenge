package com.gustavobonilla.safebodachallenge.presentation.di

import android.content.Context
import com.gustavobonilla.safebodachallenge.presentation.SafeBodaChallengeApplication
import com.gustavobonilla.safebodachallenge.presentation.splash.di.SplashComponent
import com.gustavobonilla.safebodachallenge.presentation.splash.di.SplashModule
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [SafeBodaChallengeApplicationModule::class, RepositoryModule::class, UseCaseModule::class, ViewModelFactoryModule::class,
    ViewModelModule::class])
interface SafeBodaChallengeApplicationComponent {
    fun plus(splashModule: SplashModule):SplashComponent
}

@Module
class SafeBodaChallengeApplicationModule(private val app: SafeBodaChallengeApplication) {

    @Provides
    @Singleton
    fun providesContext(): Context {
       return app
    }

    @Provides
    @Singleton
    fun providesApplication(): SafeBodaChallengeApplication {
        return app
    }
}