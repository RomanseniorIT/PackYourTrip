package com.example.packyourtrip.ui.checklist.things

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.ThingModel

class DefaultThingAdapter() : RecyclerView.Adapter<DefaultThingViewHolder>() {
    private val listThings: MutableList<ThingModel> = mutableListOf(
        ThingModel("Паспорт", false),
        ThingModel("Билет", false),
        ThingModel("Книга", false),
        ThingModel("Ноутбук", false),
        ThingModel("Зарядка для ноутбука", false),
        ThingModel("Зарядка для ", false),
        ThingModel("Зарядка для ноу", false),
        ThingModel("Зарядка для ноутбукаЗарядка для ноутбука", false),
        ThingModel("Плавки", false)
    )

    private val listToCompare = mutableListOf<ThingModel>()

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

    fun setData(list: List<ThingModel>) {
        listThings.clear()
        listThings.addAll(list)
        syncWithCheckList()
    }

    private fun syncWithCheckList() {
        listThings.forEach { default ->
            listToCompare.forEach { thing ->
                default.isDone = default.title == thing.title
            }
        }
        notifyDataSetChanged()
    }

    fun syncWithCheckList(list: List<ThingModel>) {
        listToCompare.clear()
        listToCompare.addAll(list)
        listThings.forEach { default ->
            val isSame = listToCompare.firstOrNull { thing ->
                default.title == thing.title
            }
            default.isDone = isSame != null
        }
        notifyDataSetChanged()
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {

        fun addDefault(thingModel: ThingModel)

    }
}