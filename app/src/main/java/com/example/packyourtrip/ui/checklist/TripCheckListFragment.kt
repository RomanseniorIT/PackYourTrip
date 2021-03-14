package com.example.packyourtrip.ui.checklist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.packyourtrip.R
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.databinding.FragmentThingChecklistBinding
import com.example.packyourtrip.databinding.FragmentTripChecklistBinding
import com.example.packyourtrip.injectViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TripCheckListFragment() : DaggerFragment(R.layout.fragment_trip_checklist), ShareDialog.Callback {
    private lateinit var tripCheckListFragmentAdapter: TripCheckListFragmentAdapter
    private var _binding: FragmentTripChecklistBinding? = null
    private val binding get() = _binding!!
    private var tripId: String = ""
    private val shareTitle = getString(R.string.action_share)
    private val saveTitle = getString(R.string.action_save)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: TripCheckListViewModel

    @Inject
    lateinit var db: FirebaseFirestore

    var registration: ListenerRegistration? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTripChecklistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        initView()

        viewModel.getTrip(tripId)
        startTripListener()
    }

    private fun init() {
        viewModel = injectViewModel(viewModelFactory)
        tripId = arguments?.getString(TRIP_ID) ?: ""
        tripCheckListFragmentAdapter = TripCheckListFragmentAdapter(this)
    }

    private fun initView() {
        binding.toolbar.inflateMenu(R.menu.menu_checklist)
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_white_24dp)
        activity?.setActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { onClickNavigation() }
        setHasOptionsMenu(true)

        binding.pager.adapter = tripCheckListFragmentAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Вещи"
                1 -> tab.text = "Дела"
            }
        }.attach()
    }

    private fun startTripListener() {
        var trip: TripModel? = null
        val query = db.collection("trips").document(tripId!!)
        registration = query.addSnapshotListener { snapshot, e ->
            if (e != null) {
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                viewModel.getTrip(tripId!!)
            } else {
                Log.d("TAG", "Current data: null")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            shareTitle -> startShareDialog()
            saveTitle -> findNavController().popBackStack()
        }
        return true
    }

    private fun onClickNavigation() {
        findNavController().popBackStack()
    }

    private fun startShareDialog(){
        val shareDialog = ShareDialog()
        shareDialog.setCallback(this)
        shareDialog.show(parentFragmentManager, "ShareDialog")
    }

    companion object {
        private const val TRIP_ID = "trip_id"
        @JvmStatic
        fun newInstance() =
            TripCheckListFragment()
    }

    override fun share(email: String) {
        viewModel.shareTrip(tripId, email)
    }
}
