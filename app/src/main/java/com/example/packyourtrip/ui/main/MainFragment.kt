package com.example.packyourtrip.ui.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.dialog.CreateTripDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_main)
        activity?.setActionBar(toolbar)
        val mainFragmentAdapter = MainFragmentAdapter(this)
        val viewPager: ViewPager2 = view.findViewById(R.id.pager)
        viewPager.adapter = mainFragmentAdapter
        setHasOptionsMenu(true)

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Поездки"
                1 -> tab.text = "Сохраненные"
            }
        }.attach()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_exit ->  findNavController().navigate(R.id.action_mainFragment_to_authFragment)
        }
        return super.onOptionsItemSelected(item)
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            MainFragment()
    }
}