package com.chobo.benewproject.register

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chobo.benewproject.Login.LoginActivity
import com.chobo.benewproject.R
import com.chobo.benewproject.start.StartActivity

class RegisterFragment : Fragment() {

    private lateinit var et_account: EditText
    private lateinit var et_password: EditText
    private lateinit var et_passwordCheck: EditText
    private lateinit var btn_next: Button
    private lateinit var btn_goLogin : Button

    lateinit var registerViewModel: RegisterViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        registerViewModel = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)

        et_account = view.findViewById(R.id.et_register_id)
        et_password = view.findViewById(R.id.et_register_password)
        et_passwordCheck = view.findViewById(R.id.et_register_passwordCheck)
        btn_next = view.findViewById(R.id.btn_register_next)
        btn_goLogin = view.findViewById(R.id.btn_register_goLogin)

        btn_next.setOnClickListener {
            nextClickEvent()
        }

        btn_goLogin.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }


    private fun nextClickEvent(){
        if (et_password.text.toString() == et_passwordCheck.text.toString() &&
            et_account.text.isNotEmpty() && et_password.text.isNotEmpty()) {

            registerViewModel.account = et_account.text.toString()
            registerViewModel.password = et_password.text.toString()

            (requireActivity() as? StartActivity)?.viewPager?.let { viewPager ->
                val currentItem = viewPager.currentItem
                if (currentItem < viewPager.adapter?.itemCount ?: 0 - 1) {
                    viewPager.setCurrentItem(currentItem + 1, true)
                }
            }
        } else {
            // 비밀번호 재확인 요청
        }
    }
}