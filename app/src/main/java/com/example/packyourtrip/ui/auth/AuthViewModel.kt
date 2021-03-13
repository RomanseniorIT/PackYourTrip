package com.example.packyourtrip.ui.auth

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.packyourtrip.data.repository.auth.AuthRepository
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _signIn = MutableLiveData<Intent>()
    val signIn: LiveData<Intent> get() = _signIn

    fun signIn() {
        _signIn.value = authRepository.signIn()
    }

}