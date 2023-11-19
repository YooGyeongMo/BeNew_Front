package com.gmlab.team_benew.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmlab.team_benew.main.MainActivity
import com.gmlab.team_benew.R
import com.gmlab.team_benew.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Response

// 다음 페이지에서 로그인 성공시 User Account 값 공유가능한 글로벌 데이터에 담아서 소통
// ID 값 응답값에서 담아온거 저장하기

class LoginActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textWatcher()

        //은닉화 및 캡슐화
        binding.btnLoginLogin.setOnClickListener{

            Login()
        }


    }

    private fun getUser() : User {
        val id : String = binding.tetLoginId.text.toString()
        val pwd : String = binding.tetLoginPw.text.toString()

        return User(id, pwd)
    }

    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun Login() {

            if(binding.tetLoginId.text.toString().isEmpty()) {
                Toast.makeText(this, "아이디가 비어있습니다" , Toast.LENGTH_SHORT).show()
                return
            }
            if(binding.tetLoginPw.text.toString().isEmpty()){
                Toast.makeText(this, "비밀번호가 비어있습니다.",Toast.LENGTH_SHORT).show()
                return
            }

            val authService = AuthService()
            authService.setLoginView(this)

            authService.login(getUser(),this)

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

    override fun onLoginSuccess() {
        Log.d("LOGIN/SUCCESS","로그인 성공")
        finish()
        startMainActivity()
    }

    override fun onLoginFailure() {
        Log.d("LOGIN/FAILURE","로그인 오류")
    }

}