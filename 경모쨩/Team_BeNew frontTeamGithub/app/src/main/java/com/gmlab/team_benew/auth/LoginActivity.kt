package com.gmlab.team_benew.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gmlab.team_benew.main.MainActivity
import com.gmlab.team_benew.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        textWatcher()
        loginLogic()
    }
    private fun textWatcher() {
        val login_id = findViewById<TextInputEditText>(R.id.tet_login_id)
        val tiLogin_id =findViewById<TextInputLayout>(R.id.ti_login_id)

        val passwordEditText = findViewById<TextInputEditText>(R.id.tet_login_pw)
        val passwordTextInputLayout = findViewById<TextInputLayout>(R.id.ti_login_pw)

        login_id.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (login_id.text!!.isEmpty()){
                    tiLogin_id.error="아이디를 입력해주세요"
                } else {
                    tiLogin_id.error=null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (passwordEditText.text!!.isEmpty()) {
                    passwordTextInputLayout.error = "비밀번호를 입력해주세요"
                } else {
                    passwordTextInputLayout.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })
    }

    private fun loginLogic(){
        val loginBtn = findViewById<Button>(R.id.btn_login_login)

        loginBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
//            finish()
        }

    }
}