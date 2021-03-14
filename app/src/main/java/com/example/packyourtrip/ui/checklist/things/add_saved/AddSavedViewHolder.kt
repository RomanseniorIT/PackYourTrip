package com.example.packyourtrip.ui.checklist.things.add_saved

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class AddSavedViewHolder(itemView: View, private val callback: AddSavedAdapter.Callback?) : RecyclerView.ViewHolder(itemView) {
    private val tvName = itemView.findViewById<TextView>(R.id.tv_name)

    fun onBind(name: String) {
        tvName.text = name
        itemView.setOnClickListener {
            callback?.addDefault(name)
        }
    }
}