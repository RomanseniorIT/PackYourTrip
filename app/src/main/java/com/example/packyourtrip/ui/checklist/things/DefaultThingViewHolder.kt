package com.example.packyourtrip.ui.checklist.things

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.ThingModel

class DefaultThingViewHolder(itemView: View, private val callback: DefaultThingAdapter.Callback?) :
    RecyclerView.ViewHolder(itemView) {
    private val tvThing: TextView = itemView.findViewById(R.id.tv_default_thing)

    fun onBind(thing: ThingModel) {
        tvThing.text = thing.title
        tvThing.isVisible = !thing.isDone!!
        tvThing.setOnClickListener {
            if (thing.isDone == false) {
                thing.isDone = true
                tvThing.isVisible = !thing.isDone!!
                callback?.addDefault(thing)
            }
        }
    }
}