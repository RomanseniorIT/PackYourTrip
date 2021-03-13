package com.example.packyourtrip.ui.main.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.data.repository.splash.SplashRepository
import com.example.packyourtrip.data.repository.trips.TripsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TripViewModel @Inject constructor(
    private val tripRepository: TripsRepository,
    private val splashRepository: SplashRepository
) : ViewModel() {

    private val _tripList = MutableLiveData<List<TripModel>>(emptyList())
    val tripList: LiveData<List<TripModel>> get() = _tripList

    fun loadTrips() {
        viewModelScope.launch(Dispatchers.IO) {
            val email = splashRepository.getCurrentUser()?.email
            if (email != null) {
                _tripList.value = tripRepository.loadTrips(email)
            }
        }
    }
}