package com.example.packyourtrip.ui.checklist.things

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.packyourtrip.R
import com.example.packyourtrip.databinding.DialogCreateThingBinding

class CreateThingDialog : DialogFragment(R.layout.dialog_create_thing) {

    private var _binding: DialogCreateThingBinding? = null
    private val binding get() = _binding!!
    private var callback: Callback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCreateThingBinding.inflate(
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

        binding.btnAdd.setOnClickListener {
            val thing = binding.etThing.text.toString()
            if (thing.isEmpty()) {
                Toast.makeText(requireActivity(), "Поле не может быть пустым", Toast.LENGTH_SHORT)
                    .show()
            } else {
                callback?.addThing(thing)
                dismiss()
            }
        }

    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun addThing(thing: String)
    }

}