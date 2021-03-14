package com.example.packyourtrip.data.repository.trips

import com.example.packyourtrip.data.model.TripModel
import kotlinx.coroutines.flow.Flow

interface TripsRepository {
    fun addTrip(tripModel: TripModel)
    fun changeTrip(tripModel: TripModel)
    fun deleteTrip(tripId: String)
    suspend fun loadTrips(userEmail: String): List<TripModel>
    fun addOwnerToTrip(tripId: String, email: String)
    suspend fun getTripById(tripId: String, userEmail: String): TripModel?
    fun startTripListener(tripId: String): Flow<TripModel>
    fun stopTripListener()
    suspend fun getDefaultThing() : TripModel
}