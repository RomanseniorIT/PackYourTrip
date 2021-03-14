package com.example.packyourtrip.ui.main.defaultlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.main.TripListener

class CreateDefaultDialog(private val listener: TripListener): DialogFragment() {
    private var etChecklistName: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setTitle("Создать чеклист")
        val v: View = inflater.inflate(R.layout.fragment_create_default_dialog, container)
        etChecklistName = v.findViewById(R.id.et_checklist_name)
        val btnSave: Button = v.findViewById(R.id.btn_save)
        btnSave.setOnClickListener { onClickBtnSave() }
        return v
    }

    private fun onClickBtnSave(){
        listener.saveBtnClicked(etChecklistName?.text.toString(),"","")
        dismiss()
    }
}