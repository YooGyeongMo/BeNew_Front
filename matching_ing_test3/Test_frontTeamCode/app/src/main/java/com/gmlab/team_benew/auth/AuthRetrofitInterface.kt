package com.gmlab.team_benew.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthRetrofitInterface {
    @POST("/register")
    fun signUp(@Body registerUser: RegisterUser) : Call<ResponseBody>


    @POST("/login")//응답값으로 토큰 받음?
    fun login(@Body user: User) : Call<LoginResult>


    // 스플래시에서 토큰 권한정보 확인시
    //토큰으로만 요청을 보내면 서버에서 이 토큰 확인후에 api에 맞게 응답함
    @GET("/user/get")
    fun adminGet(@Header("Authorization") bearerToken: String, @Query("account") account: String): Call<TokenGet>


    // 메인 액티비티에 가져오기 값
    @GET("/user/get")
    fun userGet(@Header("Authorization") bearerToken: String, @Query("account") account: String): Call<UserGet>
}