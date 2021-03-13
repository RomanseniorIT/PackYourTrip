package com.example.packyourtrip.data.repository.splash

interface SplashRepository {

    suspend fun isUserLoggedIn(): Boolean

}