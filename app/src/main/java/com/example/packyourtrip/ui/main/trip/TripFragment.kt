package com.example.packyourtrip.ui.main.trip

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.main.TripListener


class TripFragment : Fragment(R.layout.fragment_trip), TripListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
    }

    private fun initRecycler(view: View) {
        val recyclerTrips: RecyclerView = view.findViewById(R.id.recycler_trips)
        recyclerTrips.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerTrips.adapter = TripAdapter(this)

//        findNavController().navigate()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TripFragment()
    }

    override fun itemClicked() {
        findNavController().navigate(R.id.action_mainFragment_to_tripCheckListFragment)
    }
}