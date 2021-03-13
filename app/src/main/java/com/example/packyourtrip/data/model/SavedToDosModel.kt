package com.example.packyourtrip.data.model

import com.google.firebase.firestore.DocumentId

data class SavedToDosModel(
    @DocumentId
    val id: String? = null,
    var title: String? = null,
    val toDos: List<String> = mutableListOf(),
    val owner: List<String> = mutableListOf()
)
