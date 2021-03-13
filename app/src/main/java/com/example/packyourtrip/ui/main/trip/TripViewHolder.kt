package com.example.packyourtrip.ui.main.trip

import android.view.View
import android.widget.TextView
import androidx.core.util.TimeUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.main.TripListener

class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTripName: TextView = itemView.findViewById(R.id.tv_name_trip)
    private val tvTripDate: TextView = itemView.findViewById(R.id.tv_date_trip)

    fun onBind(trip: String, listener: TripListener) {
        itemView.setOnClickListener { listener.itemClicked() }
        tvTripName.text = trip
        tvTripDate.text = "12.03.2021"
    }
}