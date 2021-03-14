package com.example.packyourtrip.ui.checklist.things

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.ThingModel
import com.google.android.material.checkbox.MaterialCheckBox

class ThingViewHolder(itemView: View, private val callback: ThingAdapter.Callback?): RecyclerView.ViewHolder(itemView) {
    private var checkBox: MaterialCheckBox = itemView.findViewById(R.id.checkbox_things)
    private var deleteBtn = itemView.findViewById<ImageView>(R.id.iv_delete)

    fun onBind(thing: ThingModel){
        checkBox.text = thing.title
        checkBox.isChecked = thing.isDone ?: false
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            callback?.checkThing(
                ThingModel(
                    checkBox.text.toString(),
                    isChecked
                )
            )
        }

        deleteBtn.setOnClickListener {
            callback?.delete(thing)
        }
    }
}