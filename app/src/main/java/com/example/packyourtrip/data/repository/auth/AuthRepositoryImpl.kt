package com.example.packyourtrip.data.repository.auth

import android.content.Context
import android.content.Intent
import com.example.packyourtrip.R
import com.firebase.ui.auth.AuthUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val context: Context
) : AuthRepository {

    override fun signIn(): Intent {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.ic_launcher_foreground)
            .setTheme(R.style.LoginTheme)
            .build()
    }

    override suspend fun signOut(): Boolean {
        val signOut = withContext(Dispatchers.IO) {
            AuthUI.getInstance().signOut(context)
        }
        return signOut.isSuccessful
    }
}