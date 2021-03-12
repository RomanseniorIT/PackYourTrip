package com.example.packyourtrip.ui.checklist.actions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class ActionAdapter() : RecyclerView.Adapter<ActionViewHolder>() {
    private val listThings: MutableList<String> = mutableListOf(
        "Дело 1",
        "Дело 2",
        "Дело 3",
        "Дело 4"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        return ActionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actions, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) =
        holder.onBind(listThings.get(position))

    override fun getItemCount() = listThings.size

    fun updateData(list: List<String>) {
        listThings.clear()
        listThings.addAll(list)
        notifyDataSetChanged()
    }
}