package com.chobo.benewproject.Login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.chobo.benewproject.R
import com.chobo.benewproject.RegisterActivity
import com.chobo.benewproject.testmain.TestmainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        btn_login.setOnClickListener {
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
                            val id = jwtResponse.id

                            val intent = Intent(this@LoginActivity, TestmainActivity::class.java)
                            intent.putExtra("token", token)
                            intent.putExtra("id", id)
                            startActivity(intent)
                            finish()
                        }
                    }
                    else{

                    }
                }

                override fun onFailure(call: Call<JwtResponse>, t: Throwable) {

                }
            })
        }

        btn_register.setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}