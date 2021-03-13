package com.example.packyourtrip.ui.main.defaultlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.packyourtrip.data.model.SavedThingsModel
import com.example.packyourtrip.data.repository.things.SavedThingRepository
import com.example.packyourtrip.utils.MainPrefs
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedListsViewModel @Inject constructor(
    private val savedThingRepository: SavedThingRepository,
) : ViewModel() {

    private val _thingsList = MutableLiveData<List<SavedThingsModel>>(emptyList())
    val thingsList: LiveData<List<SavedThingsModel>> get() = _thingsList

    fun loadSavedThings() {
        viewModelScope.launch {
            val email = MainPrefs.userEmail
            if (email.isNotEmpty()) {
                _thingsList.value = savedThingRepository.loadSavedThings(email)
            }
        }
    }

}