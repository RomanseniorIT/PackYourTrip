package com.example.packyourtrip.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.packyourtrip.data.model.TripModel

class TripDiffUtil(private val oldList: List<TripModel>, private val newList: List<TripModel>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).id.equals(newList.get(newItemPosition).id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList.get(oldItemPosition).city.equals(newList.get(newItemPosition).city)
    }
}