package com.example.packyourtrip.ui.auth

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packyourtrip.data.repository.auth.AuthRepository
import com.example.packyourtrip.data.repository.splash.SplashRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val splashRepository: SplashRepository
) : ViewModel() {

    private val _signIn = MutableLiveData<Intent>()
    private val _isLoggedIn = MutableLiveData<Boolean>()

    val signIn: LiveData<Intent> get() = _signIn
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun signIn() {
        _signIn.value = authRepository.signIn()
    }

    fun isUserLoggedIn() {
        viewModelScope.launch {
            _isLoggedIn.value = splashRepository.isUserLoggedIn()
        }
    }

}