package com.example.packyourtrip.ui.checklist

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.packyourtrip.ui.checklist.actions.LeaveCheckListFragment
import com.example.packyourtrip.ui.checklist.things.ThingsCheckListFragment

class TripCheckListFragmentAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 1

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ThingsCheckListFragment()
            else -> ThingsCheckListFragment()
        }
    }
}