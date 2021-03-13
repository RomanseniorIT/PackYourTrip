package com.example.packyourtrip.ui.checklist.actions

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R

class DefaultActionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvAction: TextView = itemView.findViewById(R.id.tv_default_action)

    fun onBind(action: String) {
        tvAction.text = action
    }
}