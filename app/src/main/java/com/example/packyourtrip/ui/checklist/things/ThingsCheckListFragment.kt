package com.example.packyourtrip.ui.checklist.things

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.checklist.TripCheckListFragment


/**
 * A simple [Fragment] subclass.
 * Use the [TripCheckListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThingsCheckListFragment : Fragment(R.layout.fragment_thing_checklist) {
    private val spanCount = 5
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)

    }

    private fun initRecycler(view: View) {
        val recyclerThings: RecyclerView = view.findViewById(R.id.recycler_things)
        recyclerThings.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerThings.adapter = ThingAdapter()

        val recyclerDefaultThings: RecyclerView = view.findViewById(R.id.recycler_default_things)
        recyclerDefaultThings.layoutManager = GridLayoutManager(context, spanCount)
        recyclerDefaultThings.adapter = DefaultThingAdapter()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TripCheckListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ThingsCheckListFragment()
    }
}