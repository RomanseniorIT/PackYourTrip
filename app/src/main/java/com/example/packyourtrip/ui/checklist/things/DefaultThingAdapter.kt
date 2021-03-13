package com.example.packyourtrip.ui.checklist.things

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class DefaultThingAdapter() : RecyclerView.Adapter<DefaultThingViewHolder>() {
    private val listThings: MutableList<String> = mutableListOf(
        "Паспорт",
        "Билет",
        "Книга",
        "Ноутбук",
        "Зарядка для ноутбука",
        "Плавки"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultThingViewHolder {
        return DefaultThingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_default_things, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DefaultThingViewHolder, position: Int) =
        holder.onBind(listThings.get(position))

    override fun getItemCount() = listThings.size

    fun getItems(): List<String> = listThings

    fun updateData(list: List<String>) {
        listThings.clear()
        listThings.addAll(list)
        notifyDataSetChanged()
    }
}