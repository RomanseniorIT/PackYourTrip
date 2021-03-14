package com.example.packyourtrip.ui.main.defaultlists

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.SavedThingsModel
import com.example.packyourtrip.ui.main.TripListener

class SavedListsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvSaved: TextView = itemView.findViewById(R.id.tv_saved_list)

    fun onBind(item: SavedThingsModel, listener: TripListener) {
        itemView.setOnClickListener { listener.itemClicked(item.id ?: "") }
        tvSaved.text = item.title
    }
}