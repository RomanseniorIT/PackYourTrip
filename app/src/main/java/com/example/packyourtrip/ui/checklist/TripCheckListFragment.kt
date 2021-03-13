package com.example.packyourtrip.ui.checklist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.packyourtrip.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class TripCheckListFragment() : Fragment(R.layout.fragment_trip_checklist) {
    private lateinit var tripCheckListFragmentAdapter: TripCheckListFragmentAdapter
    private lateinit var viewPager: ViewPager2


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_checklist)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_white_24dp)
        activity?.setActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onClickNavigation() }
        setHasOptionsMenu(true)


        tripCheckListFragmentAdapter = TripCheckListFragmentAdapter(this)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = tripCheckListFragmentAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Вещи"
                1 -> tab.text = "Дела"
            }
        }.attach()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun onClickNavigation() {
        Toast.makeText(requireContext(), "kyky", Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TripCheckListFragment()
    }
}
