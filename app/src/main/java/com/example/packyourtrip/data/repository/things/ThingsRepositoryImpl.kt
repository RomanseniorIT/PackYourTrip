package com.example.packyourtrip.data.repository.things

import android.util.Log
import com.example.packyourtrip.data.model.ThingModel
import com.example.packyourtrip.data.model.TripModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ThingsRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : ThingsRepository {

    //Добавление вещи в поездку
    override fun addThingToTrip(tripId: String, thingModel: ThingModel) {
        db.collection("trips").document(tripId)
            .update("things", FieldValue.arrayUnion(thingModel))
            .addOnSuccessListener {
                Log.d(
                    TAG,
                    "DocumentSnapshot successfully updated!"
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    //Изменение в списке вещей
    //Модель уже с внесенным изменением
    override fun changeThingToTrip(tripModel: TripModel) {
        if (tripModel.id != null) {
            db.collection("trips").document(tripModel.id)
                .update("things", tripModel.things)
                .addOnSuccessListener {
                    Log.d(
                        TAG,
                        "DocumentSnapshot successfully updated!"
                    )
                }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
        }
    }

    //Удаление из списка вещей
    override fun deleteThingToTrip(tripId: String, thingModel: ThingModel) {
        db.collection("trips").document(tripId)
            .update("things", FieldValue.arrayRemove(thingModel))
    }

    companion object {
        private const val TAG = "TAG"
    }
}