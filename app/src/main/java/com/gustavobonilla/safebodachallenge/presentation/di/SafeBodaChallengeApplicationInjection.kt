package com.gustavobonilla.safebodachallenge.presentation.di

import android.content.Context
import com.gustavobonilla.safebodachallenge.presentation.SafeBodaChallengeApplication
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [SafeBodaChallengeApplicationModule::class, RepositoryModule::class, UseCaseModule::class])
interface SafeBodaChallengeApplicationComponent

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