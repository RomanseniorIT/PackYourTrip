package com.example.packyourtrip.ui.main.defaultlists

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
import com.example.packyourtrip.ui.main.trip.CreateTripDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class SavedListsFragment : DaggerFragment(), TripListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var savedListsViewModel: SavedListsViewModel
    lateinit var savedListsAdapter: SavedListsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_saved_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initRecycler(view)
        val fabBtn: FloatingActionButton = view.findViewById(R.id.btn_add_saved)
        fabBtn.setOnClickListener { onClickFabBtn() }
        savedListsViewModel.savedList.observe(viewLifecycleOwner) { savedList ->
            savedListsAdapter.bindThings(savedList)
        }
        savedListsViewModel.loadSavedThings()
    }

    private fun initRecycler(view: View) {
        val recyclerTrips: RecyclerView = view.findViewById(R.id.recycler_saved_list)
        recyclerTrips.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        savedListsAdapter = SavedListsAdapter(this)
        recyclerTrips.adapter = savedListsAdapter
    }

    private fun init() {
        savedListsViewModel = injectViewModel(viewModelFactory)
    }

    private fun onClickFabBtn() {
        CreateDefaultDialog(this).show(parentFragmentManager, "CreateDefaultDialog")
    }


    override fun itemClicked(tripId: String) {
       // findNavController().navigate(
      //      MainFragmentDirections.actionMainFragmentToTripCheckListFragment(tripId)
       // )
    }

    override fun saveBtnClicked(name: String, city: String, date: String) {


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            SavedListsFragment()
    }

}