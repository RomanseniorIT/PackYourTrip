package com.example.packyourtrip.ui.checklist.things

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.google.android.material.checkbox.MaterialCheckBox

class ThingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private var checkBox: MaterialCheckBox = itemView.findViewById(R.id.checkbox_things)

    fun onBind(string: String){
        checkBox.setText(string)
    }
}