package com.example.packyourtrip.ui.checklist.actions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class DefaultActionAdapter() : RecyclerView.Adapter<DefaultActionViewHolder>() {
    private val listActions: MutableList<String> = mutableListOf(
        "Выключить утюг",
        "Выключить газ",
        "Выключить воду",
        "Выключить свет в комнатах",
        "Оставить корм коту"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultActionViewHolder {
        return DefaultActionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_default_actions, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DefaultActionViewHolder, position: Int) =
        holder.onBind(listActions.get(position))

    override fun getItemCount() = listActions.size

    fun getItems(): List<String> = listActions

    fun updateData(list: List<String>) {
        listActions.clear()
        listActions.addAll(list)
        notifyDataSetChanged()
    }
}