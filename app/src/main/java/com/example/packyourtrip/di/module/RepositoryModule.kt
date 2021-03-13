package com.example.packyourtrip.di.module

import com.example.packyourtrip.data.repository.auth.AuthRepository
import com.example.packyourtrip.data.repository.auth.AuthRepositoryImpl
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
    fun getAuthRepository(repository: AuthRepositoryImpl): AuthRepository {
        return repository
    }
}