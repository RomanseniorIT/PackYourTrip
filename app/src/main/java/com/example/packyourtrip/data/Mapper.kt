package com.example.packyourtrip.data

import com.example.packyourtrip.data.model.ThingModel
import com.example.packyourtrip.data.model.TripModel

object Mapper {
    fun editTripModel(newThing: ThingModel, tripModel: TripModel): TripModel {
        var resultTripModel = tripModel
        val thingList = resultTripModel.things
        val oldThing = thingList.firstOrNull { it.title == newThing.title }
        oldThing?.let {
            thingList.remove(oldThing)
            thingList.add(newThing)
            thingList.sortBy { it.title }
            resultTripModel.things = thingList
        }
        return resultTripModel
    }
}