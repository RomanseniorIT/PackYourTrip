package com.example.packyourtrip.ui.main

interface TripListener {
    fun itemClicked(tripId: String)
    fun saveBtnClicked(name: String, city: String, date: String)
}