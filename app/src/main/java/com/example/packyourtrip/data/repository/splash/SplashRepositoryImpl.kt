package com.example.packyourtrip.data.repository.splash

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : SplashRepository {

    override suspend fun isUserLoggedIn(): Boolean {
        val currentUser = withContext(Dispatchers.IO) {
            auth.currentUser
        }
        return currentUser != null
    }
}