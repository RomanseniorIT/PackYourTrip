package com.example.packyourtrip.ui.checklist.things.add_saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class AddSavedAdapter() : RecyclerView.Adapter<AddSavedViewHolder>() {

    private val listThings: MutableList<String> = mutableListOf(
    )

    private var callback: Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddSavedViewHolder {
        return AddSavedViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_add_saved_list, parent, false),
            callback
        )
    }

    fun setCallback (callback: Callback){
        this.callback = callback
    }

    override fun onBindViewHolder(holder: AddSavedViewHolder, position: Int) =
        holder.onBind(listThings.get(position))

    override fun getItemCount() = listThings.size

    fun updateData(list: List<String>) {

    }

    interface Callback{
        fun addDefault (name: String)
    }

}