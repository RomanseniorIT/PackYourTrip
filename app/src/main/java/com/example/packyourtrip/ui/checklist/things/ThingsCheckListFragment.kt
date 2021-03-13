package com.example.packyourtrip.ui.checklist.things

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.databinding.FragmentThingChecklistBinding
import com.example.packyourtrip.injectViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ThingsCheckListFragment : DaggerFragment(R.layout.fragment_thing_checklist) {
    private val spanCount = 12
    private var _binding: FragmentThingChecklistBinding? = null
    private val binding get() = _binding!!
    private lateinit var thingAdapter: ThingAdapter
    private lateinit var defaultThingAdapter: DefaultThingAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: TripCheckListViewModel

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
        init()
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        binding.recyclerThings.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerThings.adapter = thingAdapter

        val layoutManager = GridLayoutManager(context, spanCount)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val items = defaultThingAdapter.getItems()
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
        binding.recyclerDefaultThings.adapter = defaultThingAdapter
    }

    private fun initObservers() {
        viewModel.tripModel.observe(viewLifecycleOwner, {
            thingAdapter.updateData(it.things)
        })
    }

    private fun init() {
        viewModel = injectViewModel(viewModelFactory)
        thingAdapter = ThingAdapter()
        defaultThingAdapter = DefaultThingAdapter()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            ThingsCheckListFragment()
    }
}