package com.example.packyourtrip.data.repository.todo

import com.example.packyourtrip.data.model.ToDoModel
import com.example.packyourtrip.data.model.TripModel

interface ToDosRepository {
    fun addToDoToTrip(tripId: String, toDoModel: ToDoModel)
    fun changeToDoToTrip(tripModel: TripModel)
    fun deleteToDoToTrip(tripId: String, toDoModel: ToDoModel)
}