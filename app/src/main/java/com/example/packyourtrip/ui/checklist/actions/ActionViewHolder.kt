package com.example.packyourtrip.ui.checklist.actions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.google.android.material.checkbox.MaterialCheckBox

class ActionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val checkBox: MaterialCheckBox = itemView.findViewById(R.id.checkbox_actions)

    fun onBind(action: String) {
        checkBox.text = action
    }
}