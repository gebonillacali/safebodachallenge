package com.gustavobonilla.safebodachallenge.presentation

import android.app.Application
import com.gustavobonilla.safebodachallenge.presentation.di.*

class SafeBodaChallengeApplication: Application() {

    lateinit var safeBodaChallengeApplicationComponent: SafeBodaChallengeApplicationComponent

    //region [Application] Lifecycle.
    override fun onCreate() {
        super.onCreate()
        safeBodaChallengeApplicationComponent = initDagger(this)
    }
    //endregion

    //region private impl
    private fun initDagger(app: SafeBodaChallengeApplication): SafeBodaChallengeApplicationComponent {
        return DaggerSafeBodaChallengeApplicationComponent.builder()
                .safeBodaChallengeApplicationModule(SafeBodaChallengeApplicationModule(app))
                .repositoryModule(RepositoryModule())
                .useCaseModule(UseCaseModule())
                .build()
    }
    //endregion


}