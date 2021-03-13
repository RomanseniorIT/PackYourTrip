package com.example.packyourtrip.ui.checklist.things

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class DefaultThingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvThing: TextView = itemView.findViewById(R.id.tv_default_thing)

    fun onBind(thing: String) {
        tvThing.text = thing
    }
}