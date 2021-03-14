package com.example.packyourtrip.ui.checklist

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
import com.example.packyourtrip.databinding.DialogSaveDefaultBinding

class SaveDefaultDialog : DialogFragment(R.layout.dialog_save_default) {

    private var _binding: DialogSaveDefaultBinding? = null
    private val binding get() = _binding!!
    private var callback: Callback? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogSaveDefaultBinding.inflate(
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

        binding.btnSave.setOnClickListener {
            val savedListName = binding.etSavedList.text.toString()
            if (savedListName.isEmpty()) {
                Toast.makeText(requireActivity(), "Поле не может быть пустым", Toast.LENGTH_SHORT)
                    .show()
            } else {
                callback?.saveList(savedListName)
                dismiss()
            }
        }

    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun saveList(savedListName: String)
    }

}
