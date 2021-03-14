package com.example.packyourtrip.data.repository.trips

import android.util.Log
import com.example.packyourtrip.data.model.TripModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TripsRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : TripsRepository {

    var registration: ListenerRegistration? = null

    //Добавление записи поездки
    override fun addTrip(tripModel: TripModel) {
        db.collection("trips").add(tripModel)
            .addOnSuccessListener { Log.d(TAG, "Successfully insert!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error insert document", e) }
    }

    //Редактирование поездки
    override fun changeTrip(tripModel: TripModel) {
        if (tripModel.id != null) {
            db.collection("trips").document(tripModel.id)
                .update(
                    mapOf(
                        "title" to tripModel.title,
                        "dateFrom" to tripModel.dateFrom,
                        "dateTo" to tripModel.dateTo,
                        "city" to tripModel.city
                    )
                )
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully updated!")
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
        }
    }

    //Удаление поздки
    override fun deleteTrip(tripId: String) {
        db.collection("trips").document(tripId)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    //Получение списка поездок доступных пользователю
    override suspend fun loadTrips(userEmail: String): List<TripModel> {
        val trips = mutableListOf<TripModel>()
        withContext(Dispatchers.IO) {
            db.collection("trips")
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

    override suspend fun getDefaultThing() : TripModel {
        return loadTrips("DEFAULT")[0]
    }

    //Слушатель изменений в поездке
    @ExperimentalCoroutinesApi
    override fun startTripListener(tripId: String): Flow<TripModel> =
/*        var trip: TripModel? = null
        val query = db.collection("trips").document(tripId)
        registration = query.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                trip = snapshot.toObject(TripModel::class.java)
            } else {
                Log.d(TAG, "Current data: null")
            }
        }*/
    // return trip
        //flowViaChannel
        channelFlow<TripModel> {// channel -
            // >
            var trip: TripModel? = null
            val query = db.collection("trips").document(tripId)
            registration = query.addSnapshotListener { snapshot, error ->
                if (error != null) {
                } else {
                    if (snapshot != null && snapshot.exists()) {
                        trip = snapshot.toObject(TripModel::class.java)
                        launch {
                            // if (trip != null)
                            send(trip!!)
                        }
                    }
                }
            }
        }


    override fun stopTripListener() {
        registration?.remove()
    }

    //Слушатель изменений в поездке
    override suspend fun getTripById(tripId: String, userEmail: String): TripModel? {
        val trips = mutableListOf<TripModel>()
        withContext(Dispatchers.IO) {
            db.collection("trips")
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
        val trip = trips.find { it.id == tripId }
        trip?.things?.sortBy { it.title }
        return trip
    }

    // Добавление пользователя в поездку
    override fun addOwnerToTrip(tripId: String, email: String) {
        db.collection("trips").document(tripId)
            .update("owner", FieldValue.arrayUnion(email))
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    companion object {
        private const val TAG = "TAG"
    }
}