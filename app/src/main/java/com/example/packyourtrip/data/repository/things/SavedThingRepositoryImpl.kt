package com.example.packyourtrip.data.repository.things

import android.util.Log
import com.example.packyourtrip.data.model.TripModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavedThingRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : SavedThingRepository {

    //Добавление записи поездки
    override fun addSavedList(tripModel: TripModel) {
        db.collection("savedThings").add(tripModel)
            .addOnSuccessListener { Log.d(TAG, "Successfully insert!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error insert document", e) }
    }

    //Редактирование поездки
    override fun changeSavedList(tripModel: TripModel) {
        if (tripModel.id != null) {
            db.collection("savedThings").document(tripModel.id)
                .update(
                    mapOf(
                        "title" to tripModel.title,
                    )
                )
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
        }
    }

    //Удаление поздки
    override fun deleteSavedList(tripId: String) {
        db.collection("savedThings").document(tripId)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    //Получение списка поездок доступных пользователю
    override suspend fun loadSavedList(userEmail: String): List<TripModel> {
        val trips = mutableListOf<TripModel>()
        withContext(Dispatchers.IO) {
            db.collection("savedThings")
                .whereArrayContains("owner", userEmail)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val trip = document.toObject(TripModel::class.java)
                        trips.add(trip)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
                .await()
        }
        return trips
    }


    //Слушатель изменений в поездке
    override suspend fun getSavedListById(tripId: String, userEmail: String): TripModel? {
        val trips = mutableListOf<TripModel>()
        withContext(Dispatchers.IO) {
            db.collection("savedThings")
                .whereArrayContains("owner", userEmail)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val trip = document.toObject(TripModel::class.java)
                        trips.add(trip)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
                .await()
        }
        return trips.find { it.id == tripId }
    }

    // Добавление пользователя в поездку
    override fun addOwnerToSavedList(tripId: String, email: String) {
        db.collection("savedThings").document(tripId)
            .update("owner", email)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    companion object {
        private const val TAG = "TAG"
    }
}