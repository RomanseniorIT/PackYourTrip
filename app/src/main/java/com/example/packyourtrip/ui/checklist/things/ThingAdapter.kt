package com.example.packyourtrip.ui.checklist.things

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.ThingModel

class ThingAdapter() : RecyclerView.Adapter<ThingViewHolder>() {
    private val listThings: MutableList<ThingModel> = mutableListOf()
    private var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        return ThingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_things, parent, false),
            callback
        )
    }

    override fun onBindViewHolder(holder: ThingViewHolder, position: Int) =
        holder.onBind(listThings[position])

    override fun getItemCount(): Int = listThings.size

    fun updateData(list: List<ThingModel>) {
        listThings.clear()
        listThings.addAll(list)
        notifyDataSetChanged()
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun checkThing(thingModel: ThingModel)

        fun delete(thingModel: ThingModel)
    }
}