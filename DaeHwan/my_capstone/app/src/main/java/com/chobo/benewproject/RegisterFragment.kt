package com.chobo.benewproject

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    private lateinit var et_id: EditText
    private lateinit var et_password: EditText
    private lateinit var et_passwordCheck: EditText
    private lateinit var btn_next: Button
    private lateinit var btn_goLogin : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        et_id = view.findViewById(R.id.et_register_id)
        et_password = view.findViewById(R.id.et_register_password)
        et_passwordCheck = view.findViewById(R.id.et_register_passwordCheck)
        btn_next = view.findViewById(R.id.btn_register_next)
        btn_goLogin = view.findViewById(R.id.btn_register_goLogin)

        btn_next.setOnClickListener {
            if (et_password.text.toString() == et_passwordCheck.text.toString() &&
                et_id.text.isNotEmpty() && et_password.text.isNotEmpty()) {

                val bundle = Bundle()
                bundle.putString("account", et_id.text.toString())
                bundle.putString("password", et_password.text.toString())

                val registerInfoFragment = RegisterInfoFragment()
                registerInfoFragment.arguments = bundle

                val fragmentTransaction = childFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.flay_register_next, registerInfoFragment)
                fragmentTransaction.commit()
            } else {
                // 비밀번호 재확인 요청
            }
        }

        btn_goLogin.setOnClickListener {
            val fragmentTransaction = childFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flay_register_next, LoginFragment())
            fragmentTransaction.commit()
        }

        return view
    }
}