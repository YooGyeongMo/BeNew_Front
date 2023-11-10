package com.gmlab.team_benew.start

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gmlab.team_benew.R
import com.gmlab.team_benew.auth.LoginActivity
import com.gmlab.team_benew.auth.SignUpActivity

class IntroActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btnLogin: Button = findViewById(R.id.btn_intro_login)
        val btnRegister : Button = findViewById(R.id.btn_intro_register)

        btnLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }


    }
}