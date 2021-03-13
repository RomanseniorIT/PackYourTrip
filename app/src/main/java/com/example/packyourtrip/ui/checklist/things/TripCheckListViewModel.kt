package com.example.packyourtrip.ui.checklist.things

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.data.repository.things.ThingsRepository
import com.example.packyourtrip.data.repository.trips.TripsRepository
import com.example.packyourtrip.utils.MainPrefs
import kotlinx.coroutines.launch
import javax.inject.Inject

class TripCheckListViewModel @Inject constructor(
    private val repository: ThingsRepository,
    private val tripsRepository: TripsRepository
) : ViewModel() {

    private val _tripModel = MutableLiveData<TripModel>()
    val tripModel: LiveData<TripModel> get() = _tripModel

    fun getTrip(tripId: String) {
        viewModelScope.launch {
            _tripModel.value = tripsRepository.getTripById(tripId, MainPrefs.userEmail)
        }
    }
}