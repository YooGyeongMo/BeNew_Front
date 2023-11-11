package com.gmlab.team_benew.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gmlab.team_benew.main.MainActivity
import com.gmlab.team_benew.R
import com.gmlab.team_benew.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textWatcher()
        loginLogic()
    }

    private fun textWatcher() {
        val loginId = binding.tetLoginId
        val tiLoginId = binding.tiLoginId

        val passwordEditText = binding.tetLoginPw
        val passwordTextInputLayout = binding.tiLoginPw

        loginId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (loginId.text!!.isEmpty()) {
                    tiLoginId.error = "아이디를 입력해주세요"
                } else {
                    tiLoginId.error = null
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

    private fun loginLogic() {
        val loginBtn = binding.btnLoginLogin

        loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // finish()
        }
    }
}