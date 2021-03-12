package com.example.packyourtrip.ui.checklist.actions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class DefaultActionAdapter() : RecyclerView.Adapter<DefaultActionViewHolder>() {
    private val listThings: MutableList<String> = mutableListOf(
        "Стандартное Дело 1",
        "Стандартное Дело 2",
        "Стандартное Дело 3",
        "Стандартное Дело 4"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultActionViewHolder {
        return DefaultActionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_default_actions, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DefaultActionViewHolder, position: Int) =
        holder.onBind(listThings.get(position))

    override fun getItemCount() = listThings.size

    fun updateData(list: List<String>) {
        listThings.clear()
        listThings.addAll(list)
        notifyDataSetChanged()
    }
}