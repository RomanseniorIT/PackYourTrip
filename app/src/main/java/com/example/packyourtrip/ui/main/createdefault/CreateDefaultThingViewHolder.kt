package com.example.packyourtrip.ui.main.createdefault

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.ThingModel

class CreateDefaultThingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val tvThing: TextView = itemView.findViewById(R.id.tv_default_thing)

    fun onBind(thing: ThingModel) {
        tvThing.text = thing.title
    }
}