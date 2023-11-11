package com.gmlab.team_benew.auth

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gmlab.team_benew.R
import com.gmlab.team_benew.databinding.ActivitySignupBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity: AppCompatActivity(), SignUpView {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterRegister.setOnClickListener{
            signUp()
        }
     }

    private fun getUser() : User {
        val id : String = binding.tetSignupId.text.toString()
        val pwd : String = binding.tetSignupPw.text.toString()

        return User(id, pwd)
    }

    private fun signUp() {
        if(binding.tetSignupId.text.toString().isEmpty()) {
            Toast.makeText(this, "아이디가 비어있습니다" , Toast.LENGTH_SHORT).show()
            return
        }
        if(binding.tetSignupPw.text.toString() != binding.tetSignupPwRecheck.text.toString()){
            Toast.makeText(this, "비밀번호가 일치하지 않아요! 재확인해주세요",Toast.LENGTH_SHORT).show()
            return
        }


        val authService = AuthService()
        authService.setSignUpView(this)

        authService.signUp(getUser())
    }

    override fun onSignUpSuccess() {
       finish()
    }

    override fun onSignUpFailure() {
        Log.d("SIGNUP/FAILURE","회원가입오류")
    }


}