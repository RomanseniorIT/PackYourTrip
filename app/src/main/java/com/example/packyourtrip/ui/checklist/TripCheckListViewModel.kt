package com.example.packyourtrip.ui.checklist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packyourtrip.data.model.ThingModel
import com.example.packyourtrip.data.model.ToDoModel
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.data.repository.things.SavedThingRepository
import com.example.packyourtrip.data.repository.things.SavedThingRepositoryImpl
import com.example.packyourtrip.data.repository.things.ThingsRepository
import com.example.packyourtrip.data.repository.trips.TripsRepository
import com.example.packyourtrip.utils.MainPrefs
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class TripCheckListViewModel @Inject constructor(
    private val thingsRepository: ThingsRepository,
    private val tripsRepository: TripsRepository,
    private val savedThingRepository: SavedThingRepository
) : ViewModel() {

    private val _tripModel = MutableLiveData<TripModel>()
    val tripModel: LiveData<TripModel> get() = _tripModel

    private val _defThings = MutableLiveData<List<ThingModel>>()
    val defThings: LiveData<List<ThingModel>> get() = _defThings

    private val _defActions = MutableLiveData<List<ToDoModel>>()
    val defActions: LiveData<List<ToDoModel>> get() = _defActions

    private val _savedList = MutableLiveData<List<TripModel>>()
    val savedList: LiveData<List<TripModel>> get() = _savedList

    fun getTrip(tripId: String) {
        viewModelScope.launch {
            _tripModel.value = tripsRepository.getTripById(tripId, MainPrefs.userEmail)
            startTripListener(tripId)
        }
    }

    fun getDefaultList() {
        viewModelScope.launch {
            _defThings.value = tripsRepository.getDefaultThing().things
            _defActions.value = tripsRepository.getDefaultThing().toDos
        }
    }

    fun addThing(tripId: String, thing: String) {
        thingsRepository.addThingToTrip(tripId, thing)
    }

    fun changeThingToTrip(tripModel: TripModel) {
        thingsRepository.changeThingToTrip(tripModel)
    }

    fun deleteThing(tripId: String, thingModel: ThingModel) {
        thingsRepository.deleteThingToTrip(tripId, thingModel)
    }

    fun shareTrip(tripId: String, email: String) {
        tripsRepository.addOwnerToTrip(tripId, email)
    }

    fun startTripListener(tripId: String) {
        viewModelScope.launch {
            try {
                val trip = tripsRepository.startTripListener(tripId).first()

                if (trip != null) _tripModel.value =
                    tripsRepository.startTripListener(tripId).first()//.asLiveData()
            } catch (e: NoSuchElementException) {
                Log.d("TAG", "startTripListener: $e ")
            }
        }
    }

    fun stopTripListener() {
        tripsRepository.stopTripListener()
    }

    fun addSavedList(tripModel: TripModel) {
        savedThingRepository.addSavedList(tripModel)
    }

    fun loadSavedList(){
        viewModelScope.launch {
            _savedList.value = savedThingRepository.loadSavedList(MainPrefs.userEmail)
        }
    }

    override fun onCleared() {
        super.onCleared()
        stopTripListener()
    }
}