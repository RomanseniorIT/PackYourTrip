package com.example.packyourtrip.data.repository.things

import com.example.packyourtrip.data.model.TripModel

interface SavedThingRepository {
    fun addSavedList(tripModel: TripModel)
    fun changeSavedList(tripModel: TripModel)
    fun deleteSavedList(tripId: String)
    suspend fun loadSavedList(userEmail: String): List<TripModel>
    fun addOwnerToSavedList(tripId: String, email: String)
    suspend fun getSavedListById(tripId: String, userEmail: String): TripModel?


}