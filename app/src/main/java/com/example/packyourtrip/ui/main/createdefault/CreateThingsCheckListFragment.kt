package com.example.packyourtrip.ui.main.createdefault

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R


class CreateThingsCheckListFragment : Fragment() {
    private val spanCount = 12

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_things_check_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler(view)
    }

    private fun initRecycler(view: View) {
        val recyclerThings: RecyclerView = view.findViewById(R.id.recycler_things)
        recyclerThings.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerThings.adapter = CreateThingAdapter()

        val adapter = CreateDefaultThingAdapter()
        val layoutManager = GridLayoutManager(context, spanCount)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val items = adapter.getItems()
                val item = items[position].title
                val itemLength = item?.length ?: -1
                return when {
                    itemLength < 1 -> 0
                    itemLength < 5 -> 2
                    itemLength < 9 -> 3
                    itemLength < 14 -> 4
                    itemLength < 21 -> 6
                    else -> 12
                }
            }
        }

    }
    companion object {

        @JvmStatic
        fun newInstance() =
            CreateThingsCheckListFragment()
    }
}