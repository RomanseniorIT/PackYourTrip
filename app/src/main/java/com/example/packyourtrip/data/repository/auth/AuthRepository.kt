package com.example.packyourtrip.data.repository.auth

interface AuthRepository {

    suspend fun isUserLoggedIn(): Boolean

    suspend fun logOut()
}