package com.example.packyourtrip.ui.checklist.actions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.packyourtrip.R

class LeaveCheckListFragment() : Fragment(R.layout.fragment_leave_checklist) {
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
        val recyclerActions: RecyclerView = view.findViewById(R.id.recycler_actions)
        recyclerActions.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerActions.adapter = ActionAdapter()

        val recyclerDefaultActions: RecyclerView = view.findViewById(R.id.recycler_default_actions)
        recyclerDefaultActions.layoutManager = GridLayoutManager(context, spanCount)
        recyclerDefaultActions.adapter = DefaultActionAdapter()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            LeaveCheckListFragment()

    }
}