package com.example.packyourtrip.data.repository.things

import com.example.packyourtrip.data.model.ThingModel
import com.example.packyourtrip.data.model.TripModel

interface ThingsRepository {
    fun addThingToTrip(tripId: String, thing: String)
    fun changeThingToTrip(tripModel: TripModel)
    fun deleteThingToTrip(tripId: String, thingModel: ThingModel)
}