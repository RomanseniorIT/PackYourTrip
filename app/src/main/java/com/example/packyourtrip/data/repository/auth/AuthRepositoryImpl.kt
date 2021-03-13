package com.example.packyourtrip.data.repository.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
        private val auth: FirebaseAuth
): AuthRepository {

    override suspend fun isUserLoggedIn(): Boolean {
        val currentUser = withContext(Dispatchers.IO){
            auth.currentUser
        }

        return currentUser != null
    }

    override suspend fun logOut() {
        TODO("Not yet implemented")
    }
}