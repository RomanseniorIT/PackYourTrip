package com.example.packyourtrip.ui.main.trip

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.ui.main.TripListener

class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTripName: TextView = itemView.findViewById(R.id.tv_name_trip)
    private val tvTripCity: TextView = itemView.findViewById(R.id.tv_city)
    private val tvTripDate: TextView = itemView.findViewById(R.id.tv_date_trip)
    private val tvCity: TextView = itemView.findViewById(R.id.tv_city)

    fun onBind(trip: TripModel, listener: TripListener) {
        itemView.setOnClickListener { listener.itemClicked(trip.id ?: "") }
        tvTripName.text = trip.title
        tvTripCity.text = trip.city
        tvTripDate.text = trip.dateFrom.toString()
        tvCity.text = trip.city
    }
}