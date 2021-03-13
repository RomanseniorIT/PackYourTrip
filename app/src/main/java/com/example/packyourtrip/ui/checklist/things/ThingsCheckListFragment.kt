package com.example.packyourtrip.ui.checklist.things

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.databinding.FragmentThingChecklistBinding


class ThingsCheckListFragment : Fragment(R.layout.fragment_thing_checklist) {
    private val spanCount = 12
    private var _binding: FragmentThingChecklistBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThingChecklistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

    }

    private fun initRecycler() {
        binding.recyclerThings.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerThings.adapter = ThingAdapter()

        val adapter = DefaultThingAdapter()
        val layoutManager = GridLayoutManager(context, spanCount)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val items = adapter.getItems()
                val item = items[position]
                val itemLength = item.length
                return when {
                    itemLength < 8 -> 2
                    itemLength < 9 -> 3
                    itemLength < 18 -> 4
                    else -> 12
                }
            }
        }
        binding.recyclerDefaultThings.layoutManager = layoutManager
        binding.recyclerDefaultThings.adapter = adapter
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ThingsCheckListFragment()
    }
}