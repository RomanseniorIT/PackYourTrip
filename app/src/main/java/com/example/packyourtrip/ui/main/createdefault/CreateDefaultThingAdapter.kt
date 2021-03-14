package com.example.packyourtrip.ui.main.createdefault

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.ThingModel

class CreateDefaultThingAdapter(): RecyclerView.Adapter<CreateDefaultThingViewHolder>() {
    private val listThings: MutableList<ThingModel> = mutableListOf(
        ThingModel(title = "Паспорт"),
        ThingModel(title = "Билет"),
        ThingModel(title = "Книга"),
        ThingModel(title = "Ноутбук"),
        ThingModel(title = "Зарядка для ноутбука"),
        ThingModel(title = "Зарядка для "),
        ThingModel(title = "Зарядка для ноу"),
        ThingModel(title = "Зарядка для ноутбукаЗарядка для ноутбука"),
        ThingModel(title = "Плавки")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateDefaultThingViewHolder {
        return CreateDefaultThingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_default_things, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CreateDefaultThingViewHolder, position: Int) =
        holder.onBind(listThings[position])

    override fun getItemCount() = listThings.size

    fun getItems(): List<ThingModel> = listThings

    fun updateData(list: List<ThingModel>) {
        listThings.clear()
        listThings.addAll(list)
        notifyDataSetChanged()
    }
}