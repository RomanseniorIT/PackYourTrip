package com.example.packyourtrip.ui.checklist.things

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class ThingAdapter() : RecyclerView.Adapter<ThingViewHolder>() {
    private val listThings: MutableList<String> = mutableListOf(
        "Вещь 1",
        "Вещь 2",
        "Вещь 3",
        "Вещь 4"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThingViewHolder {
        return ThingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_things, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ThingViewHolder, position: Int) =
        holder.onBind(listThings.get(position))

    override fun getItemCount(): Int = listThings.size


    fun updateData(list: List<String>) {
        listThings.clear()
        listThings.addAll(list)
        notifyDataSetChanged()
    }
}