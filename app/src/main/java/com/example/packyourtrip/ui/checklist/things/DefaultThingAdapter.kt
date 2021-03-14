package com.example.packyourtrip.ui.checklist.things

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.ThingModel

class DefaultThingAdapter() : RecyclerView.Adapter<DefaultThingViewHolder>() {
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

    private var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultThingViewHolder {
        return DefaultThingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_default_things, parent, false), callback
        )
    }

    override fun onBindViewHolder(holder: DefaultThingViewHolder, position: Int) =
        holder.onBind(listThings[position])

    override fun getItemCount() = listThings.size

    fun getItems(): List<ThingModel> = listThings

    fun updateData(list: List<ThingModel>) {
        listThings.clear()
        listThings.addAll(list)
        notifyDataSetChanged()
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {

        fun addDefault(thingModel: ThingModel)

    }
}