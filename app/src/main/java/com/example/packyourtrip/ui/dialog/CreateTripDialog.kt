package com.example.packyourtrip.ui.dialog

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.packyourtrip.R
import com.example.packyourtrip.ui.main.TripListener


class CreateTripDialog(private val listener: TripListener) : DialogFragment() {
    private var mDisplayDate: EditText? = null
    private var mDateSetListener: OnDateSetListener? = null
    private var etTrip: EditText? = null
    private var etCity: EditText? = null
    private var etDate: EditText? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setTitle("Создать поездку")
        val v: View = inflater.inflate(R.layout.fragment_dialog, container)
        etTrip = v.findViewById(R.id.et_name_trip)
        etCity = v.findViewById(R.id.et_city)
        etDate = v.findViewById(R.id.et_date)
        val btnSave: Button = v.findViewById(R.id.btn_save)
        btnSave.setOnClickListener { onClickBtnSave() }
        editDate(v)
        return v
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun editDate(view: View) {
        mDisplayDate = view.findViewById(R.id.et_date);
        mDisplayDate!!.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]
            val dialog = DatePickerDialog(
                requireContext(),
                android.R.style.Theme_Holo_Dialog_MinWidth, mDateSetListener, year, month, day
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
        mDateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date = "$day/$month/$year"
                mDisplayDate?.setText(date, TextView.BufferType.EDITABLE)
            }
    }

    private fun onClickBtnSave() {
        listener.saveBtnClicked(etTrip?.text.toString(), etCity?.text.toString(), etDate?.text.toString())
        dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
    }
}