package com.example.packyourtrip.ui.main.createdefault

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CreateDefaultFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreateThingsCheckListFragment()
            else -> CreateLeaveCheckListFragment()
        }
    }
}