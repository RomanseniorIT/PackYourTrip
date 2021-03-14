package com.example.packyourtrip.ui.checklist.things.add_saved

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.packyourtrip.R
import com.example.packyourtrip.databinding.DialogAddSavedBinding

class AddSavedDialog : DialogFragment(R.layout.dialog_add_saved), AddSavedAdapter.Callback {

    private var _binding: DialogAddSavedBinding? = null
    private val binding get() = _binding!!
    private var callback: Callback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddSavedBinding.inflate(
            inflater, container, false
        )
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = arguments?.getStringArrayList(KEY) ?: mutableListOf<String>()
        val adapter = AddSavedAdapter()
        adapter.setCallback(this)
        val rv = view.findViewById<RecyclerView>(R.id.rv_add_saved_list)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        adapter.updateData(list)
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun addSavedList(savedListName: String)
    }

    companion object {
        private const val KEY = "key"
        fun newInstance(list: ArrayList<String>): AddSavedDialog {
            val dialog = AddSavedDialog()
            val args = Bundle()
            args.putStringArrayList(KEY, list)
            dialog.arguments = args
            return dialog
        }
    }

    override fun addDefault(name: String) {
        callback?.addSavedList(name)
    }

}