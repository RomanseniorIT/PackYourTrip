package com.example.packyourtrip.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.packyourtrip.ui.main.defaultlists.SavedListsFragment
import com.example.packyourtrip.ui.main.trip.TripFragment

class MainFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TripFragment()
            else -> SavedListsFragment()
        }
    }
}