package com.example.packyourtrip.ui.main.trip

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTrip: TextView = itemView.findViewById(R.id.tv_trip)

    fun onBind(trip: String) {
        tvTrip.text = trip
    }
}