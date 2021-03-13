package com.example.packyourtrip.ui.main

import com.example.packyourtrip.data.model.TripModel

interface TripListener {
    fun itemClicked(tripId: String)
}