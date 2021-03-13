package com.example.packyourtrip.data.model

import com.google.firebase.firestore.DocumentId

data class SavedThingsModel(
    @DocumentId
    val id: String? = null,
    var title: String? = null,
    val things: List<String> = mutableListOf(),
    val owner: List<String> = mutableListOf()
)
