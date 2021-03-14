package com.example.packyourtrip.ui.main.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.ui.main.TripListener

class TripAdapter(private val listener: TripListener) : RecyclerView.Adapter<TripViewHolder>() {
    private val listTrips: MutableList<TripModel> = mutableListOf()
    private var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        return TripViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_trips_card, parent, false),
            callback
        )
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        holder.onBind(listTrips[position], listener)
    }

    override fun getItemCount(): Int = listTrips.size

    fun getTrips(): List<TripModel> = listTrips

    fun bindTrips(trips: List<TripModel>) {
        listTrips.clear()
        listTrips.addAll(trips)
        // notifyDataSetChanged()
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun delete(trip: TripModel)
    }
}