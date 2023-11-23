package com.chobo.benewproject.profilecard

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chobo.benewproject.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileCardActivity : AppCompatActivity() {

    private lateinit var imgb_picture: ImageButton
    private lateinit var et_nickname: EditText
    private lateinit var et_email: EditText
    private lateinit var et_introduce: EditText
    private lateinit var btn_modify: Button
    private lateinit var tv_gender : TextView
    private lateinit var tv_birthday : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_card)

        val token = intent.getStringExtra("token")
        val memberId = intent.getStringExtra("id")

        imgb_picture = findViewById(R.id.imgb_profileCard_picture)
        et_nickname = findViewById(R.id.et_profileCard_nickname)
        et_email = findViewById(R.id.et_profileCard_email)
        et_introduce = findViewById(R.id.et_profileCard_introduce)
        btn_modify = findViewById(R.id.btn_profileCard_modify)
        tv_gender = findViewById(R.id.tv_profileCard_gender)
        tv_birthday = findViewById(R.id.tv_profileCard_birthday)

        imgb_picture.clipToOutline = true

        disableInputFields()
        if (memberId != null) {
            if (token != null) {
                getProfileToServer(token, memberId)
            }
        }

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

    }

    private fun getProfileToServer(token : String, memberId : String){

        if(memberId == null){
            return
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(getProfileRequest::class.java)

        val call: Call<getProfileData> = apiService.getProfile("Bearer $token", memberId)

        call?.enqueue(object : Callback<getProfileData> {
            override fun onResponse(call: Call<getProfileData>, response: Response<getProfileData>) {
                if (response.isSuccessful) {
                    val profileData: getProfileData? = response.body()
                    profileData?.let {
                        val nickname = it.nickname
                        val instruction = it.instruction
                        val personalLink = it.personalLink
                        val projectExperience = it.projectExperience
                        val role = it.role

                        val birthday = it.member.birthday
                        val email = it.member.email
                        val gender = it.member.gender
                        val major = it.member.major
                        val phoneNumber = it.member.phoneNumber

                        runOnUiThread {
                            et_nickname.setText(nickname)
                            et_email.setText(email)
                            et_introduce.setText(instruction)
                            tv_gender.text = gender
                            tv_birthday.text = birthday
                        }
                        //UI에 저장()

                    }
                } else {
                }
            }

            override fun onFailure(call: Call<getProfileData>, t: Throwable) {

            }
        })

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
        et_nickname.isEnabled = false
        et_email.isEnabled = false
        et_introduce.isEnabled = false
    }

    private fun enableInputFields() {
        imgb_picture.isEnabled = true
        et_nickname.isEnabled = true
        et_email.isEnabled = true
        et_introduce.isEnabled = true
    }
}