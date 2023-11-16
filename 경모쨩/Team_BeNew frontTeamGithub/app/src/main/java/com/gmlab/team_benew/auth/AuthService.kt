package com.gmlab.team_benew.auth

import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody
import kotlin.math.sign

class AuthService {
    private lateinit var signUpView: SignUpView
    private lateinit var loginView: LoginView

    fun setSignUpView(signUpView: SignUpView){
        this.signUpView = signUpView
    }

    fun signUp(user : User) {

                val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.signUp(user).enqueue(object: Callback<ResponseBody> {
            //응답이 왔을때
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("SIGNUP/SUCCESS", response.toString())
                when(response.code()){
                    200-> signUpView.onSignUpSuccess()
                    else -> signUpView.onSignUpFailure()

                }

            }
            //실패 했을때
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("SIGNUP/FAILURE",t.message.toString()) // 비동기 작업
            }


        }) // enqueue에서 응답을 처리함.

        Log.d("SIGNUP","비동기 함수 작동완료 ~!")
    }

}