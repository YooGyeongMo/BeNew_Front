package com.chobo.benewproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {
    private lateinit var btn_login : Button
    private lateinit var btn_register : Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        btn_login = view.findViewById(R.id.btn_login_login)
        btn_register = view.findViewById(R.id.btn_login_register)

        btn_register.setOnClickListener {
            val fragmentTransaction = childFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.flay_login_register, RegisterFragment())
            fragmentTransaction.commit()
        }

        btn_login.setOnClickListener {//로그인 이벤트 필요
            val intent = Intent(requireContext(), TestmainActivity::class.java)
            startActivity(intent)

            requireActivity().finish()
        }

        return view
    }
}