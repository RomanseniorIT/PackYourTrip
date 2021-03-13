package com.example.packyourtrip.data.repository.things

import android.util.Log
import com.example.packyourtrip.data.model.SavedThingsModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavedThingRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : SavedThingRepository {

    //Добавление списка сохраненных вещей
    override fun addSavedThings(savedThingsModel: SavedThingsModel) {
        db.collection("savedThings").add(savedThingsModel)
            .addOnSuccessListener { Log.d(TAG, "Successfully insert!") }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error insert document", e)
            }
    }

    //Добавление вещей в список сохраненных
    override fun addThingToSaved(savedThingsListId: String, thing: String) {
        db.collection("savedThings").document(savedThingsListId)
            .update("things", FieldValue.arrayUnion(thing))
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    //Изменение в списке вещей
    //Модель уже с внесенным изменением
    override fun changeSavedThings(savedThingsModel: SavedThingsModel) {
        if (savedThingsModel.id != null) {
            db.collection("savedThings").document(savedThingsModel.id)
                .update("things", savedThingsModel.things)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error updating document", e)
                }
        }
    }

    //Получение списков сохраеннных вещей доступных пользователю
    override suspend fun loadSavedThings(userEmail: String): List<SavedThingsModel> {
        val savedThingsList = mutableListOf<SavedThingsModel>()
        withContext(Dispatchers.IO) {
            db.collection("savedThings")
                .whereArrayContains("owner", userEmail)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val savedList = document.toObject(SavedThingsModel::class.java)
                        savedThingsList.add(savedList)
                    }

                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
                .await()
        }
        return savedThingsList
    }

    //Удаление из списка вещей
    override fun deleteSavedThings(savedThingsListId: String, thing: String) {
        db.collection("savedThings").document(savedThingsListId)
            .update("things", FieldValue.arrayRemove(thing))
    }

    companion object {
        private const val TAG = "TAG"
    }
}