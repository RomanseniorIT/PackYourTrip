package com.example.packyourtrip.ui.main

interface TripListener {
    fun itemClicked(tripId: String)
    fun saveBtnClicked(tripName: String, city: String, date: String)
}