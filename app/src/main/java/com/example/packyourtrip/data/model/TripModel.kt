package com.example.packyourtrip.data.model

data class TripModel(
    val id: String? = null,
    val title: String? = null,
    val dateFrom: Long? = null,
    val dateTo: Long? = null,
    val city: String? = null,
    val things: MutableList<ThingModel> = mutableListOf(),
    val toDos: MutableList<ToDoModel> = mutableListOf(),
    val owner: MutableList<String> = mutableListOf()
)
