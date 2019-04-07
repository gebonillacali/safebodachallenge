package com.gustavobonilla.safebodachallenge.presentation.di

import android.arch.persistence.room.Room
import com.gustavobonilla.safebodachallenge.data.SafeBodaDataRepository
import com.gustavobonilla.safebodachallenge.data.api.LuftansaServiceApi
import com.gustavobonilla.safebodachallenge.data.api.LuftansaServiceApiImpl
import com.gustavobonilla.safebodachallenge.data.room.AppDatabase
import com.gustavobonilla.safebodachallenge.domain.repository.SafeBodaRepository
import com.gustavobonilla.safebodachallenge.presentation.SafeBodaChallengeApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(appDatabase: AppDatabase, serviceApi: LuftansaServiceApi): SafeBodaRepository {
        return SafeBodaDataRepository(serviceApi, appDatabase)
    }

    @Provides
    @Singleton
    fun providesApi(): LuftansaServiceApi {
        return LuftansaServiceApiImpl()
    }

    @Provides
    @Singleton
    fun providesAppDatabase(application: SafeBodaChallengeApplication): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "safeboda-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}