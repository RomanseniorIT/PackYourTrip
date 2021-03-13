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
    private val spanCount = 12
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
        val adapter = DefaultActionAdapter()
        val layoutManager = GridLayoutManager(context, spanCount)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val items = adapter.getItems()
                val item = items.get(position)
                val itemLength = item.length
                return when {
                    itemLength < 8 -> 2
                    itemLength < 9 -> 3
                    itemLength < 18 -> 4
                    else -> 12
                }
            }
        }
        recyclerDefaultActions.layoutManager = layoutManager //GridLayoutManager(context, spanCount)
        recyclerDefaultActions.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            LeaveCheckListFragment()

    }
}