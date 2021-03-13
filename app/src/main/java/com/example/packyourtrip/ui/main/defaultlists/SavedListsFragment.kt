package com.example.packyourtrip.ui.main.defaultlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.injectViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class SavedListsFragment : DaggerFragment() {

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
        savedListsViewModel.thingsList.observe(viewLifecycleOwner) { thingsList ->
            savedListsAdapter.bindThings(thingsList)
        }
        savedListsViewModel.loadSavedThings()
    }

    private fun initRecycler(view: View) {
        val recyclerTrips: RecyclerView = view.findViewById(R.id.recycler_saved_list)
        recyclerTrips.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        savedListsAdapter = SavedListsAdapter()
        recyclerTrips.adapter = savedListsAdapter
    }

    private fun init() {
        savedListsViewModel = injectViewModel(viewModelFactory)
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            SavedListsFragment()
    }
}