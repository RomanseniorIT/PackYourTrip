package com.example.packyourtrip.data.model

import com.google.firebase.firestore.DocumentId

data class TripModel(
    @DocumentId
    val id: String? = null,
    var title: String? = null,
    var dateFrom: Long? = null,
    var dateTo: String? = null,
    var city: String? = null,
    val things: MutableList<ThingModel> = mutableListOf(),
    val toDos: MutableList<ToDoModel> = mutableListOf(),
    val owner: MutableList<String> = mutableListOf()
)
