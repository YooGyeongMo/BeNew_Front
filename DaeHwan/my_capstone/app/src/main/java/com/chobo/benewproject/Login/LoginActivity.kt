package com.chobo.benewproject.Login

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.chobo.benewproject.R

class LoginActivity : AppCompatActivity() {

    private lateinit var btn_login : Button
    private lateinit var btn_register : Button
    private lateinit var et_account : EditText
    private lateinit var et_password : EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login = findViewById(R.id.btn_login_login2)
        btn_register = findViewById(R.id.btn_login_register2)
        et_account = findViewById(R.id.et_login_account2)
        et_password = findViewById(R.id.et_login_password2)

        btn_register.setOnClickListener {
        }

    }
}