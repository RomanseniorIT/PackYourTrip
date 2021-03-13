package com.example.packyourtrip.ui.main.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.main.TripListener

class TripAdapter(private val listener : TripListener) : RecyclerView.Adapter<TripViewHolder>() {
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
                .inflate(R.layout.view_holder_trips_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.onBind(listTrips.get(position), listener)
    }

    override fun getItemCount(): Int = listTrips.size
}