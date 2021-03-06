package com.example.packyourtrip.ui.main.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.ui.main.TripListener

class TripAdapter(private val listener : TripListener) : RecyclerView.Adapter<TripViewHolder>() {
    private val listTrips: MutableList<TripModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        return TripViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_trips_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.onBind(listTrips[position], listener)
    }

    override fun getItemCount(): Int = listTrips.size

    fun bindTrips(trips: List<TripModel>) {
        listTrips.clear()
        listTrips.addAll(trips)
        notifyDataSetChanged()
    }
}