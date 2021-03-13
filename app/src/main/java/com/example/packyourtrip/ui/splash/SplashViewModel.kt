package com.example.packyourtrip.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packyourtrip.data.repository.splash.SplashRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val splashRepository: SplashRepository
) : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    fun isUserLoggedIn() {
        viewModelScope.launch {
            _isLoggedIn.value = splashRepository.isUserLoggedIn()
        }
    }
}