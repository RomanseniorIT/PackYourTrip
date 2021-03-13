package com.example.packyourtrip.data.repository.todo

import android.util.Log
import com.example.packyourtrip.data.model.SavedToDosModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavedToDosRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : SavedToDosRepository {
    //Добавление списка сохраненных дел
    override fun addSavedToDos(savedToDosModel: SavedToDosModel) {
        db.collection("savedToDos").add(savedToDosModel)
            .addOnSuccessListener { Log.d(TAG, "Successfully insert!") }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error insert document", e)
            }
    }

    //Добавление дел в список сохраненных
    override fun addToDoToSaved(savedToDosListId: String, toDo: String) {
        db.collection("savedToDos").document(savedToDosListId)
            .update("toDos", FieldValue.arrayUnion(toDo))
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    //Изменение в списке дел
    //Модель уже с внесенным изменением
    override fun changeSavedToDos(savedToDosModel: SavedToDosModel) {
        if (savedToDosModel.id != null) {
            db.collection("savedThings").document(savedToDosModel.id)
                .update("toDos", savedToDosModel.toDos)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error updating document", e)
                }
        }
    }

    //Получение списков сохраеннных дел доступных пользователю
    override suspend fun loadSavedToDos(userEmail: String): List<SavedToDosModel> {
        val savedToDosList = mutableListOf<SavedToDosModel>()
        withContext(Dispatchers.IO) {
            db.collection("savedThings")
                .whereArrayContains("owner", userEmail)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val savedList = document.toObject(SavedToDosModel::class.java)
                        savedToDosList.add(savedList)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
                .await()
        }
        return savedToDosList
    }

    //Удаление из списка дел
    override fun deleteSavedToDos(savedToDosListId: String, toDo: String) {
        db.collection("savedThings").document(savedToDosListId)
            .update("toDos", FieldValue.arrayRemove(toDo))
    }

    companion object {
        private const val TAG = "TAG"
    }
}