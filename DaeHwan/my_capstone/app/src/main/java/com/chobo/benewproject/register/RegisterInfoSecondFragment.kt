package com.chobo.benewproject.register

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.chobo.benewproject.Login.LoginActivity
import com.chobo.benewproject.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar

class RegisterInfoSecondFragment : Fragment() {

    lateinit var et_name : EditText
    lateinit var btn_birthday : Button
    lateinit var spn_gender : Spinner
    lateinit var spn_major : Spinner
    lateinit var btn_register : Button

    lateinit var registerViewModel: RegisterViewModel

    @SuppressLint("MissingInflatedId")
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

        registerViewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)



        btn_birthday.setOnClickListener {
            showDatePickerDialog()
        }

        btn_register.setOnClickListener {
            if(et_name.text.isNotEmpty() && btn_birthday.text.isNotEmpty()){

                val account = registerViewModel.account
                val password = registerViewModel.password
                val email = registerViewModel.email
                val phoneNumber = registerViewModel.phoneNumber

                val name = et_name.text.toString()
                val gender = spn_gender.selectedItem.toString()
                val birthday = btn_birthday.text.toString()
                val major = spn_major.selectedItem.toString()

                val retrofit = Retrofit.Builder()
                    .baseUrl("http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                //http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com/ 주소

                val apiService = retrofit.create(DoRegister::class.java)

                val request = RegisterData(account, password, name, gender, birthday, email, major, phoneNumber)

                val call = apiService.signup(request)

                call.enqueue(object : Callback<Boolean> {
                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        if (response.isSuccessful) {
                            val intent = Intent(activity, LoginActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
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
                val selectedDate = String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDayOfMonth)
                btn_birthday.text = selectedDate
            },
            year, month, dayOfMonth
        )
        datePickerDialog.show()
    }

}