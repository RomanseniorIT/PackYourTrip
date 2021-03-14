package com.example.packyourtrip.ui.main.defaultlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.data.repository.things.SavedThingRepository
import com.example.packyourtrip.utils.MainPrefs
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedListsViewModel @Inject constructor(
    private val savedThingRepository: SavedThingRepository,
) : ViewModel() {

    private val _savedList = MutableLiveData<List<TripModel>>(emptyList())
    val savedList: LiveData<List<TripModel>> get() = _savedList

    fun loadSavedThings() {
        viewModelScope.launch {
            val email = MainPrefs.userEmail
            if (email.isNotEmpty()) {
                _savedList.value = savedThingRepository.loadSavedList(email)
            }
        }
    }

    fun addSavedThings(tripModel: TripModel) {
        viewModelScope.launch {
            val email = MainPrefs.userEmail
            tripModel.owner.add(email)
            savedThingRepository.addSavedList(tripModel)
            loadSavedThings()
        }
    }

}