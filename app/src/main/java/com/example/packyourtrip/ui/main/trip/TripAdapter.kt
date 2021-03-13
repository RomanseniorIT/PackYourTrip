package com.example.packyourtrip.ui.main.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class TripAdapter() : RecyclerView.Adapter<TripViewHolder>() {
    private val listTrips: MutableList<String> = mutableListOf(
        "Поездка 1",
        "Поездка 2",
        "Поездка 3",
        "Поездка 4",
        "Поездка 5",
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        return TripViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_trips, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.onBind(listTrips.get(position))
    }

    override fun getItemCount(): Int = listTrips.size
}