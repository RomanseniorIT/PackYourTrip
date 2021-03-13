package com.example.packyourtrip.data.repository.splash

import com.example.packyourtrip.utils.MainPrefs
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
        MainPrefs.userEmail = currentUser?.email ?: ""
        return currentUser != null
    }
}