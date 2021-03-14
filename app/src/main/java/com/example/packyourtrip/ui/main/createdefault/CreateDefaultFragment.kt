package com.example.packyourtrip.ui.main.createdefault

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.main.MainFragmentAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class CreateDefaultFragment : Fragment(R.layout.fragment_create_default) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_main)
        activity?.setActionBar(toolbar)
        val createDefaultFragmentAdapter = CreateDefaultFragmentAdapter(this)
        val viewPager: ViewPager2 = view.findViewById(R.id.pager)
        viewPager.adapter = createDefaultFragmentAdapter
        setHasOptionsMenu(true)

        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Вещи"
                1 -> tab.text = "Дела"
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.action_exit ->  findNavController().navigate(R.id.action_mainFragment_to_authFragment)
//        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CreateDefaultFragment()
    }
}