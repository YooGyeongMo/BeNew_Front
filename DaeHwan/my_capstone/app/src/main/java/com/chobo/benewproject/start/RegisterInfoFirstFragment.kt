package com.chobo.benewproject.start

import android.annotation.SuppressLint
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.chobo.benewproject.R

class RegisterInfoFirstFragment : Fragment() {

    lateinit var btn_next : Button
    lateinit var et_email : EditText
    lateinit var et_phone : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register_info_first, container, false)

        btn_next = view.findViewById(R.id.btn_registerInfoFirst_next)
        et_email = view.findViewById(R.id.et_registerInfoFirst_email)
        et_phone = view.findViewById(R.id.et_registerInfoFirst_phone)

        et_phone.addTextChangedListener(PhoneNumberFormattingTextWatcher())


        btn_next.setOnClickListener {
            if(et_email.text.isNotEmpty() && et_phone.text.length == 13)
            {


                (requireActivity() as? StartActivity)?.viewPager?.let { viewPager ->
                    val currentItem = viewPager.currentItem
                    if (currentItem < viewPager.adapter?.itemCount ?: 0 - 1) {
                        viewPager.setCurrentItem(currentItem + 1, true)
                    }
                }
            }
            else
            {
                //에딧텍스트 비어있음 요청
            }
        }

        return view
    }

}