package com.example.packyourtrip.data.repository.auth

import android.content.Intent

interface AuthRepository {

    fun signIn(): Intent

    suspend fun signOut(): Boolean
}