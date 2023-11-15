package com.chobo.benewproject.profilecard

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.chobo.benewproject.R

class ProfileCardFragment : Fragment() {

    private lateinit var imgb_picture: ImageButton
    private lateinit var et_name: EditText
    private lateinit var et_email: EditText
    private lateinit var et_introduce: EditText
    private lateinit var btn_modify: Button
    private lateinit var spn_gender : Spinner
    private lateinit var spn_age : Spinner

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_card, container, false)

        imgb_picture = view.findViewById(R.id.imgb_profileCard_picture)
        et_name = view.findViewById(R.id.et_profileCard_name)
        et_email = view.findViewById(R.id.et_profileCard_email)
        et_introduce = view.findViewById(R.id.et_profileCard_introduce)
        btn_modify = view.findViewById(R.id.btn_profileCard_modify)
        spn_gender = view.findViewById(R.id.spn_profileCard_gender)
        spn_age = view.findViewById(R.id.spn_profileCard_age)

        imgb_picture.clipToOutline = true

        disableInputFields()

        imgb_picture.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 1)
        }

        btn_modify.setOnClickListener {
            if (btn_modify.text.toString() == "수정") {
                enableInputFields()

                btn_modify.text = "저장"
            } else {
                disableInputFields()

                btn_modify.text = "수정"
            }
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            val selectedImage : Uri? = data.data
            imgb_picture.setImageURI(selectedImage)
        }
    }

    private fun disableInputFields() {
        imgb_picture.isEnabled = false
        et_name.isEnabled = false
        et_email.isEnabled = false
        et_introduce.isEnabled = false
        spn_age.isEnabled = false
        spn_gender.isEnabled = false
    }

    private fun enableInputFields() {
        imgb_picture.isEnabled = true
        et_name.isEnabled = true
        et_email.isEnabled = true
        et_introduce.isEnabled = true
        spn_age.isEnabled = true
        spn_gender.isEnabled = true
    }
}