package com.gmlab.team_benew.start

import android.content.Context
import android.content.Intent
import android.util.Log
import com.gmlab.team_benew.auth.AuthResponse
import com.gmlab.team_benew.auth.AuthRetrofitInterface
import com.gmlab.team_benew.auth.LoginActivity
import com.gmlab.team_benew.auth.TokenGet
import com.gmlab.team_benew.auth.User
import com.gmlab.team_benew.auth.getRetrofit
import com.gmlab.team_benew.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashAuthService {

    private lateinit var splashView: SplashView

    fun setSplashView(splashView: SplashView) {
        this.splashView = splashView
    }


    fun verifyUserToken(token: String) {
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)
        authService.adminGet(token).enqueue(object : Callback<TokenGet> {
            override fun onResponse(call: Call<TokenGet>, response: Response<TokenGet>) {
                Log.d("NETWORK_VERIFY_USER/SUCCESS","USER_TOKEN_VAILD")
                when (response.code()) {
                    200 -> // 토큰이 유효한 경우, Success
                        splashView.onTokenCheckSuccess()

                    401 ->
                        // 토큰이 유효하지 않음 실패
                        splashView.onTokenCheckFailure()

                    else -> {
                        Log.d("SplashCheck/Error","체크 오류")
                    }
                }
            }

            override fun onFailure(call: Call<TokenGet>, t: Throwable) {
                Log.d("NETWORK_VERIFY_USER/FAILURE" , "스플래쉬 네트워크 오류 에러")
            }
        })
    }

}