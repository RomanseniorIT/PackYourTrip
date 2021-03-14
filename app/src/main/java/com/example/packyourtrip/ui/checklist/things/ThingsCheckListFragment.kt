package com.example.packyourtrip.ui.checklist.things

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.data.Mapper
import com.example.packyourtrip.data.model.ThingModel
import com.example.packyourtrip.data.model.TripModel
import com.example.packyourtrip.databinding.FragmentThingChecklistBinding
import com.example.packyourtrip.ui.checklist.TripCheckListFragment
import com.example.packyourtrip.ui.checklist.TripCheckListViewModel
import dagger.android.support.DaggerFragment

class ThingsCheckListFragment : DaggerFragment(R.layout.fragment_thing_checklist), CreateThingDialog.Callback, ThingAdapter.Callback {
    private val spanCount = 12
    private var tripId: String = ""
    private var _binding: FragmentThingChecklistBinding? = null
    private val binding get() = _binding!!

    private lateinit var thingAdapter: ThingAdapter
    private lateinit var defaultThingAdapter: DefaultThingAdapter
    private lateinit var tripModel: TripModel

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
        binding.recyclerDefaultThings.layoutManager = layoutManager
        binding.recyclerDefaultThings.adapter = defaultThingAdapter
    }

    private fun initObservers() {
        viewModel.tripModel.observe(viewLifecycleOwner, {
            tripModel = it
            tripId = it.id ?: ""
            thingAdapter.updateData(it.things)
        })
    }

    private fun init() {
        viewModel = (parentFragment as TripCheckListFragment).viewModel
        thingAdapter = ThingAdapter()
        thingAdapter.setCallback(this)
        defaultThingAdapter = DefaultThingAdapter()

        binding.btnAddThing.setOnClickListener {
            val createThingDialog = CreateThingDialog()
            createThingDialog.setCallback(this)
            createThingDialog.show(parentFragmentManager, "CreateThingDialog")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ThingsCheckListFragment()
    }

    override fun addThing(thing: String) {
        viewModel.addThing(tripId, thing)
    }

    override fun checkThing(thingModel: ThingModel) {
        tripModel = Mapper.editTripModel(thingModel, tripModel)
        viewModel.changeThingToTrip(tripModel)
    }

    override fun delete(thingModel: ThingModel) {
        viewModel.deleteThing(tripId, thingModel)
    }
}