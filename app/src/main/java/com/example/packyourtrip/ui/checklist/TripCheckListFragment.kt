package com.example.packyourtrip.ui.checklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.packyourtrip.R
import com.example.packyourtrip.databinding.FragmentThingChecklistBinding
import com.example.packyourtrip.databinding.FragmentTripChecklistBinding
import com.example.packyourtrip.injectViewModel
import com.example.packyourtrip.ui.checklist.things.TripCheckListViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TripCheckListFragment() : DaggerFragment(R.layout.fragment_trip_checklist) {
    private lateinit var tripCheckListFragmentAdapter: TripCheckListFragmentAdapter
    private var _binding: FragmentTripChecklistBinding? = null
    private val binding get() = _binding!!
    private var tripId: String? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: TripCheckListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTripChecklistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = injectViewModel(viewModelFactory)
        tripId = arguments?.getString(TRIP_ID) ?: ""
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_checklist)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_white_24dp)
        activity?.setActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onClickNavigation() }
        setHasOptionsMenu(true)

        tripCheckListFragmentAdapter = TripCheckListFragmentAdapter(this)

        binding.pager.adapter = tripCheckListFragmentAdapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            when (position) {
                0 -> tab.text = "????????"
                1 -> tab.text = "????????"
            }
        }.attach()

        tripId?.let {
            viewModel.getTrip(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun onClickNavigation() {
        findNavController().popBackStack()
    }

    companion object {
        private const val TRIP_ID = "trip_id"
        @JvmStatic
        fun newInstance() =
            TripCheckListFragment()
    }
}
