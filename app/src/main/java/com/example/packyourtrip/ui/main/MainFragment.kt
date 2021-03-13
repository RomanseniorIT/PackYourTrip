package com.example.packyourtrip.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.packyourtrip.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mainFragmentAdapter = MainFragmentAdapter(this)
        val viewPager: ViewPager2 = view.findViewById(R.id.pager)
        viewPager.adapter = mainFragmentAdapter

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Поездки"
                1 -> tab.text = "Сохраненные"
            }
        }.attach()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment()
    }
}