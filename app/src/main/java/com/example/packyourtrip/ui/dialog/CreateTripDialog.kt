package com.example.packyourtrip.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.packyourtrip.R

class CreateTripDialog: DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setTitle("Создать поездку")
        val v: View = inflater.inflate(R.layout.fragment_dialog, container)
        val btnSave: Button = v.findViewById(R.id.btn_save)
        btnSave.setOnClickListener { onClickBtnSave() }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun onClickBtnSave(){

    }
}