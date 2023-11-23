package com.chobo.benewproject.start

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import com.chobo.benewproject.R
import java.util.Calendar

class RegisterInfoSecondFragment : Fragment() {

    lateinit var et_name : EditText
    lateinit var btn_birthday : Button
    lateinit var spn_gender : Spinner
    lateinit var spn_major : Spinner
    lateinit var btn_register : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_info_second, container, false)

        et_name = view.findViewById(R.id.et_registerInfoSecond_name)
        btn_birthday = view.findViewById(R.id.btn_registerInfoSecond_birthdate)
        spn_gender = view.findViewById(R.id.spn_registerInfoSecond_gender)
        spn_major = view.findViewById(R.id.spn_registerInfoSecond_major)
        btn_register = view.findViewById(R.id.btn_registerInfoSecond_register)

        btn_birthday.setOnClickListener {
            showDatePickerDialog()
        }



        btn_register.setOnClickListener {
            if(et_name.text.isNotEmpty() && btn_birthday.text.isNotEmpty()){

            }
        }


        return view
    }

    private fun showDatePickerDialog(){
        val calendar = Calendar.getInstance()

        calendar.set(2000, 1, 1)

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { view: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                val selectedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDayOfMonth)
                btn_birthday.text = selectedDate
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

}