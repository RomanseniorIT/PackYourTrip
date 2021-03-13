package com.example.packyourtrip.ui.main.trip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R


class TripFragment : Fragment(R.layout.fragment_trip) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
    }

    private fun initRecycler(view: View) {
        val recyclerThings: RecyclerView = view.findViewById(R.id.recycler_trips)
        recyclerThings.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerThings.adapter = TripAdapter()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TripFragment()
    }
}