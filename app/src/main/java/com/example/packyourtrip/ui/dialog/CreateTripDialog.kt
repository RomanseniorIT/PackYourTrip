package com.example.packyourtrip.ui.dialog

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.main.TripListener

class CreateTripDialog(private val listener: TripListener): DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setTitle("Создать поездку")
        val v: View = inflater.inflate(R.layout.fragment_dialog, container)
        val btnSave: Button = v.findViewById(R.id.btn_save)
        btnSave.setOnClickListener { onClickBtnSave() }
        return v
    }

    private fun onClickBtnSave(){
       listener.saveBtnClicked()
        // findNavController().navigate(R.id.action_mainFragment_to_tripCheckListFragment)
        dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}