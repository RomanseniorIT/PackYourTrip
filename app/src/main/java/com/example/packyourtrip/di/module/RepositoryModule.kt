package com.example.packyourtrip.di.module

import com.example.packyourtrip.data.repository.auth.AuthRepository
import com.example.packyourtrip.data.repository.auth.AuthRepositoryImpl
import com.example.packyourtrip.data.repository.splash.SplashRepository
import com.example.packyourtrip.data.repository.splash.SplashRepositoryImpl
import com.example.packyourtrip.data.repository.things.SavedThingRepository
import com.example.packyourtrip.data.repository.things.SavedThingRepositoryImpl
import com.example.packyourtrip.data.repository.things.ThingsRepository
import com.example.packyourtrip.data.repository.things.ThingsRepositoryImpl
import com.example.packyourtrip.data.repository.trips.TripsRepository
import com.example.packyourtrip.data.repository.trips.TripsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun getTripsRepository(repository: TripsRepositoryImpl): TripsRepository {
        return repository
    }

    @Singleton
    @Provides
    fun getSplashRepository(repository: SplashRepositoryImpl): SplashRepository {
        return repository
    }

    @Singleton
    @Provides
    fun getAuthRepository(repository: AuthRepositoryImpl): AuthRepository {
        return repository
    }

    @Singleton
    @Provides
    fun getThingsCheckListRepository(repository: ThingsRepositoryImpl): ThingsRepository {
        return repository
    }

    @Singleton
    @Provides
    fun getSavedThingsRepository(repository: SavedThingRepositoryImpl): SavedThingRepository {
        return repository
    }
}