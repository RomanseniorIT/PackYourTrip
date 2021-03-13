package com.example.packyourtrip

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.packyourtrip.data.model.ThingModel
import com.example.packyourtrip.data.model.TripModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    lateinit var textView: TextView
    lateinit var btnLoad: Button
    lateinit var btnLogout: Button
    lateinit var btnChangeTrip: Button
    lateinit var btnAddThing: Button
    lateinit var btnChangeThing: Button
    lateinit var btnListener: Button
    val trips = mutableListOf<TripModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        db = Firebase.firestore
        textView = findViewById(R.id.textView)
        btnLoad = findViewById(R.id.btnLoad)
        btnLogout = findViewById(R.id.btnLogout)
        btnChangeTrip = findViewById(R.id.btnChangeTrip)
        btnAddThing = findViewById(R.id.btnAddThing)
        btnChangeThing = findViewById(R.id.btnChangeThing)
        btnListener = findViewById(R.id.btnListener)
        btnLoad.setOnClickListener {
            loadTrips()
        }
        btnLogout.setOnClickListener {
            signOut()
        }
        btnChangeTrip.setOnClickListener {
            val trip = trips[0]
            trip.title = "NEEEW22 TRIP"
            trip.city = "NEW CITY22"
            if (trip != null) {
                changeTrip(trip)
            }
        }
        btnAddThing.setOnClickListener {
            val trip = trips[0]
            if (trip.id != null)
            addThingToTrip(trip.id, ThingModel("NEW THING2", false))
        }
        btnChangeThing.setOnClickListener {
            val trip = trips[0]
            trip.things[1].title = "EDITEDD!"
            trip.things[3].isDone = true
            if (trip != null)
            changeThingToTrip(trip)
        }
        btnListener.setOnClickListener {
            startListener()
        }
    }

    //Изменение в списке вещей
    //Модель уже с внесенным изменением
    private fun changeThingToTrip(trip: TripModel) {
        db.collection("trips").document(trip.id!!)
            .update("things",trip.things)
    }

    //Добавление вещи в поездку
    private fun addThingToTrip(id: String, thingModel: ThingModel) {
        db.collection("trips").document(id)
            .update("things", FieldValue.arrayUnion(thingModel))
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    //Редактирование поездки
    private fun changeTrip(tripModel: TripModel) {
        if (tripModel.id != null)
            db.collection("trips").document(tripModel.id)
                .update(
                    mapOf(
                        "title" to tripModel.title,
                        "dateFrom" to tripModel.dateFrom,
                        "dateTo" to tripModel.dateTo,
                        "city" to tripModel.city
                    )
                )
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
    }

    // Добавление записи поездки
    private fun addTrip() {
        val currentUser = auth.currentUser
        if (currentUser != null)
            db.collection("trips").add(
                TripModel(
                    title = "new",
                    things = mutableListOf(ThingModel("Passport23", false)),
                    owner = mutableListOf(currentUser.email)
                )
            )
    }

    //Получение списка поездок доступных пользователю
    private fun loadTrips() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            trips.clear()
            db.collection("trips")
                .whereArrayContains("owner", currentUser.email)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val trip = document.toObject(TripModel::class.java)
                        trips.add(trip)
                    }
                    textView.text = trips.toString()
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
        }
    }

    private fun startListener() {
        //Слушатель изменений
        val trip = trips[0]
        val docRef = db.collection("trips").document(trip.id!!)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                for (querySnapshot in snapshot.data)
                textView.text = snapshot.data
                Log.d(TAG, "Current data: ${snapshot.data}")
            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(this, "Уже авторизован: ${currentUser.email}", Toast.LENGTH_LONG).show();
            //addTrip()
        } else {
            createSignInIntent()
        }
    }

    private fun signOut() {
        AuthUI.getInstance().signOut(this)
    }

    private fun createSignInIntent() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
            //, AuthUI.IdpConfig.PhoneBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_launcher_foreground)
                .build(),
            RC_SIGN_IN
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this, "Авторизация: ${user.email}", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(
                    this,
                    "Ошибка авторизации: ${response?.error?.errorCode}",
                    Toast.LENGTH_LONG
                ).show();
            }
        }
    }

    companion object {
        private const val TAG = "TAG"
        private const val RC_SIGN_IN = 100
    }

}