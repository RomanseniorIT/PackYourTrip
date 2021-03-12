package com.example.packyourtrip.data.model

import com.google.firebase.firestore.DocumentId

data class TripModel(
    @DocumentId
    val id: String? = null,
    val title: String? = null,
    val dateFrom: Long? = null,
    val dateTo: Long? = null,
    val city: String? = null,
    val things: MutableList<ThingModel> = mutableListOf(),
    val toDos: MutableList<ToDoModel> = mutableListOf(),
    val owner: MutableList<String> = mutableListOf()
)
