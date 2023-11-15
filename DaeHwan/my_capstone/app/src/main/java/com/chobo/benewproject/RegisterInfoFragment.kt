package com.chobo.benewproject

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar

class RegisterInfoFragment : Fragment() {

    private lateinit var btn_join : Button
    private lateinit var et_name : EditText
    private lateinit var et_phone : EditText
    private lateinit var et_nickname : EditText
    private lateinit var et_email : EditText
    private lateinit var btn_birthdate : Button
    private lateinit var spn_gender : Spinner
    private lateinit var spn_major : Spinner
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_info, container, false)

        btn_join = view.findViewById(R.id.btn_registerInfo_join)
        et_name = view.findViewById(R.id.et_registerInfo_name)
        et_phone = view.findViewById(R.id.et_registerInfo_phone)
        et_nickname = view.findViewById(R.id.et_registerInfo_nickname)
        et_email = view.findViewById(R.id.et_registerInfo_email)
        btn_birthdate = view.findViewById(R.id.btn_registerInfo_birthdate)
        spn_gender = view.findViewById(R.id.spn_registerInfo_gender)
        spn_major = view.findViewById(R.id.spn_registerInfo_major)

        val account = arguments?.getString("account")!!
        val password = arguments?.getString("password")!!

        et_phone.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        btn_birthdate.setOnClickListener {
            showDatePickerDialog()
        }

        btn_join.setOnClickListener {

            var name = et_name.text.toString()
            var gender = spn_gender.selectedItem.toString()
            var birthdate = btn_birthdate.text.toString()
            var email = et_email.text.toString()
            var major = spn_major.selectedItem.toString()
            var phoneNumber = et_phone.text.toString()

            if(name.isNotEmpty() && birthdate.isNotEmpty() && email.isNotEmpty() && phoneNumber.length == 14){

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                //http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com/ 주소

                val apiService = retrofit.create(DoRegister::class.java)

                val request = RegisterData(account, password, name, gender, birthdate, email, major, phoneNumber)

                val call = apiService.signup(request)

                call.enqueue(object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        if (response.isSuccessful) {
                            val fragmentTransaction = childFragmentManager.beginTransaction()
                            fragmentTransaction.replace(R.id.flay_register_next, LoginFragment())
                            fragmentTransaction.commit()
                        } else{
                            
                        }
                    }

                    override fun onFailure(call: Call<Boolean>, t: Throwable) {

                    }

                })

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
                val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDayOfMonth"
                btn_birthdate.text = selectedDate
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }
    }
