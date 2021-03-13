package com.example.packyourtrip.data.repository.todo

import com.example.packyourtrip.data.model.SavedToDosModel

interface SavedToDosRepository {
    fun addSavedToDos(savedToDosModel: SavedToDosModel)
    fun addToDoToSaved(savedToDosListId: String, toDo: String)
    fun changeSavedToDos(savedToDosModel: SavedToDosModel)
    suspend fun loadSavedToDos(userEmail: String) : List<SavedToDosModel>
    fun deleteSavedToDos(savedToDosListId: String, toDo: String)
}