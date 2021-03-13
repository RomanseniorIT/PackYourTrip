package com.example.packyourtrip.ui.main.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.injectViewModel
import com.example.packyourtrip.ui.main.MainFragmentDirections
import com.example.packyourtrip.ui.main.TripListener
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class TripFragment : DaggerFragment(), TripListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var tripViewModel: TripViewModel
    lateinit var tripAdapter: TripAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecycler(view)
        tripViewModel.tripList.observe(viewLifecycleOwner) { trips ->
            tripAdapter.bindTrips(trips)
        }
        tripViewModel.loadTrips()
    }

    private fun initRecycler(view: View) {
        val recyclerTrips: RecyclerView = view.findViewById(R.id.recycler_trips)
        recyclerTrips.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        tripAdapter = TripAdapter(this)
        recyclerTrips.adapter = tripAdapter

//        findNavController().navigate()
    }

    private fun init() {
        tripViewModel = injectViewModel(viewModelFactory)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TripFragment()
    }

    override fun itemClicked(tripId: String) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToTripCheckListFragment(tripId)
        )
    }
}