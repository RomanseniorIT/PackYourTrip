package com.example.packyourtrip.data.repository.todo

import android.util.Log
import com.example.packyourtrip.data.model.ToDoModel
import com.example.packyourtrip.data.model.TripModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class ToDosRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : ToDosRepository {

    //Добавление дел в поездку
    override fun addToDoToTrip(tripId: String, toDoModel: ToDoModel) {
        db.collection("trips").document(tripId)
            .update("toDos", FieldValue.arrayUnion(toDoModel))
            .addOnSuccessListener {
                Log.d(
                    TAG,
                    "DocumentSnapshot successfully updated!"
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    //Изменение в списке дел
    //Модель уже с внесенным изменением
    override fun changeToDoToTrip(tripModel: TripModel) {
        if (tripModel.id != null) {
            db.collection("trips").document(tripModel.id)
                .update("toDos", tripModel.toDos)
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
    override fun deleteToDoToTrip(tripId: String, toDoModel: ToDoModel) {
        db.collection("trips").document(tripId)
            .update("toDos", FieldValue.arrayRemove(toDoModel))
    }

    companion object {
        private const val TAG = "TAG"
    }
}