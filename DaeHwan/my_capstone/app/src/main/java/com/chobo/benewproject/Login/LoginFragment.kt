package com.chobo.benewproject.Login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.chobo.benewproject.R
import com.chobo.benewproject.TestmainActivity
import com.chobo.benewproject.register.RegisterFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {
    private lateinit var btn_login : Button
    private lateinit var btn_register : Button
    private lateinit var et_account : EditText
    private lateinit var et_password : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        btn_login = view.findViewById(R.id.btn_login_login)
        btn_register = view.findViewById(R.id.btn_login_register)
        et_account = view.findViewById(R.id.et_login_account)
        et_password = view.findViewById(R.id.et_login_password)

        btn_register.setOnClickListener {
            val fragmentTransaction = childFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flay_login_register, RegisterFragment())
            fragmentTransaction.commit()
        }


        btn_login.setOnClickListener {//로그인 이벤트 필요

            val account = et_account.text.toString()
            val password = et_password.text.toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(LoginRequest::class.java)

            val request = LoginData(account, password)

            apiService.login(request).enqueue(object : Callback<JwtResponse> {
                override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                    if(response.isSuccessful){

                        val jwtResponse = response.body()

                        if(jwtResponse != null && jwtResponse.token.isNotEmpty())
                        {
                            val token = jwtResponse.token

                            val intent = Intent(requireContext(), TestmainActivity::class.java)
                            intent.putExtra("token", token)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                    }
                    else{

                    }
                }

                override fun onFailure(call: Call<JwtResponse>, t: Throwable) {

                }
            })
        }

        return view
    }
}