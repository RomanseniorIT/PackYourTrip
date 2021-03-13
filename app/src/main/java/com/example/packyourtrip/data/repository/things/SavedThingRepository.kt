package com.example.packyourtrip.data.repository.things

import com.example.packyourtrip.data.model.SavedThingsModel

interface SavedThingRepository {
    fun addSavedThings(savedThingsModel: SavedThingsModel)
    fun addThingToSaved(savedThingsListId: String, thing: String)
    fun changeSavedThings(savedThingsModel: SavedThingsModel)
    suspend fun loadSavedThings(userEmail: String): List<SavedThingsModel>?
    fun deleteSavedThings(savedThingsListId: String, thing: String)
}