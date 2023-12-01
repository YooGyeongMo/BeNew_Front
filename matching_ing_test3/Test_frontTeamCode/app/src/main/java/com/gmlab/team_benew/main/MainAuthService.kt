package com.gmlab.team_benew.main

import android.content.Context
import android.util.Log
import com.gmlab.team_benew.auth.AuthRetrofitInterface

import com.gmlab.team_benew.auth.TokenGet
import com.gmlab.team_benew.auth.UserGet
import com.gmlab.team_benew.auth.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainAuthService(private val context: HomeFragment) {

    private lateinit var mainView: MainView
    private var userNameCallback: UserNameCallback? = null

    fun setMainView(mainView: MainView) {
        this.mainView = mainView
    }
    //MainView를 MainAuthService에 주입, MainAuthService는 해당 클래스에서 정의한 동작을 호출할 수 있음

    fun setUserNameCallback(callback: UserNameCallback) {
        this.userNameCallback = callback
    }
    //UserNameCallback을 MainAuthService에 주입, MainAuthService는 해당 클래스에서 정의한 동작을 호출할 수 있음

    fun getUserName(token: String, account: String){
        val authService = getRetrofit().create(AuthRetrofitInterface::class.java)

        val bearerToken = "Bearer $token"


        //userget은 유저의 정보 갖고오는
        authService.userGet(bearerToken, account).enqueue(object : Callback<UserGet> {
            override fun onResponse(call: Call<UserGet>, response: Response<UserGet>) {
                when(response.code()){
                    200 -> {
                        var username =  response.body()?.name?.let{it + "님"} ?:""//username을 가져옴
                        userNameCallback?.onUserNameReceived(username)//username넘겨줌
                        mainView.onMainGetSuccess()//성공
                    }
                    401 -> {
                        mainView.onMainGetFailure()//실패
                    }
                    else -> {
                        response.code()
                    }
                }

            }

            override fun onFailure(call: Call<UserGet>, t: Throwable) {
                Log.d("NETWORK/FAILURE","MAIN ACTIVITY 네트워크 연결실패")
            }

        })
    }

}