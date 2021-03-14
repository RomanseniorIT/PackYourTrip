package com.example.packyourtrip.ui.main.defaultlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.TripModel

class SavedListsAdapter() : RecyclerView.Adapter<SavedListsViewHolder>() {
    private val list: MutableList<TripModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedListsViewHolder {
        return SavedListsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_saved_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SavedListsViewHolder, position: Int) {
        holder.onBind(list.get(position))
    }

    override fun getItemCount(): Int = list.size

    fun bindThings(trips: List<TripModel>) {
        list.clear()
        list.addAll(trips)
        notifyDataSetChanged()
    }
}