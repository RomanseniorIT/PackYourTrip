package com.example.packyourtrip.ui.main.defaultlists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.checklist.things.DefaultThingViewHolder

class SavedListsAdapter() : RecyclerView.Adapter<SavedListsViewHolder>() {
    private val list: MutableList<String> = mutableListOf(
        "Сохраненный лист 1",
        "Сохраненный лист 2",
        "Сохраненный лист 3",
        "Сохраненный лист 4",
        "Сохраненный лист 5",
    )

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
}