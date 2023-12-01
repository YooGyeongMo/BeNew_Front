package com.gmlab.team_benew.auth

import android.content.Context
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
    private lateinit var reloginView: ReLoginView

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun setLoginView(loginView: LoginView){
        this.loginView = loginView
    }

    fun setReLoginView(reloginView: ReLoginView) {
        this.reloginView =reloginView
    }


    //회원가입
    fun signUp(registerUser: RegisterUser) {

        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        authService.signUp(registerUser).enqueue(object : Callback<ResponseBody> {
            //응답이 왔을때
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.d("NETWORK_SIGNUP/SUCCESS", response.toString())
                when (response.code()) {
                    200 -> signUpView.onSignUpSuccess()
                    else -> signUpView.onSignUpFailure()

                }

            }

            //실패 했을때
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("SIGNUP_NETWORK/FAILURE", t.message.toString()) // 비동기 작업
            }


        }) // enqueue에서 응답을 처리함.

        Log.d("SIGNUP", "비동기 함수 작동완료 ~!")
    }


    //로그인
    fun login(user: User, context: Context) {

        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.login(user).enqueue(object : Callback<LoginResult> {
            //응답이 왔을때
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                Log.d("NETWORK_LOGIN/SUCCESS", response.toString())
                when (response.code()) {
                    200 -> {
                        //응답 바디가 널이 아닐때 let블록실행
                        response.body()?.let{ result->
                            val currentToken = getCurrentToken(context)
                            if( currentToken != result.token) {//즉, 현재 토큰과 응답으로 받은 토큰이 다를때 응답으로 받은 토큰이랑 아이디 새로 저장함
                            saveLoginInfo(context, result.id, result.account, result.token)
                        }
                            loginView.onLoginSuccess()
                        }
//                        GlobalData.loginId = response.body()?.result?.id //null일 경우 저장되어야해서 안전한 연산자 사용
//                        GlobalData.UserAccount = response.body()?.result?.account
//                        GlobalData.GlobalToken = response.body()?.result?.token
                    }
                    401 -> {
                        relogin(user,context)
                    }
                    else ->
                        loginView.onLoginFailure()
                }
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                Log.d("LOGIN_NETWORK/FAILURE", t.message.toString()) // 비동기 작업
            }

        })

        Log.d("LOGIN", "비동기 함수 작동완료 ~!")
    }

    private fun relogin(user: User, context: Context) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.login(user).enqueue(object: Callback<LoginResult>{
            override fun onResponse(call: Call<LoginResult>, response: Response<LoginResult>) {
                Log.d("NETWORK_RELOGIN/SUCCESS", response.toString())
                if(response.isSuccessful){
                    response.body()?.let{
                        //새로운 로그인정보를 SharedPreferences에 저장
                        saveLoginInfo(context, it.id, it.account, it.token)
                        reloginView.onReLoginSuccess()
                    }
                }
                else{
                    reloginView.onReLoginFailure()
                }
            }

            override fun onFailure(call: Call<LoginResult>, t: Throwable) {
                Log.d("NETWORK_RELOGIN/SUCCESS","다시 로그인 성공!")
            }
        })
    }

    // null 이 아닐 때 let 구분이 실행되도록 설정 요청 후 응답받는 값이 null이 아닐때 설정.
    // 최초 로그인시 저장됩니다.
    private fun saveLoginInfo(context: Context, id: Int?, account: String?, token: String?)
    {
        val sharedPref = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            id?.let { putInt("loginId", it) }//널이 아닐때만 실행됨
            account?.let { putString("userAccount", it) }
            token?.let { putString("userToken", it) }
            apply()//적용
        }
    }

    private fun getCurrentToken(context: Context): String? {
        val sharedPref = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        return sharedPref.getString("userToken", null)
    }

}