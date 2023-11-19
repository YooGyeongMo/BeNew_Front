package com.gmlab.team_benew.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthRetrofitInterface {
    @POST("/register")
    fun signUp(@Body user: User) : Call<ResponseBody>

    @POST("/login")
    fun loginCheck(@Body user: User) : Call<AuthResponse>
    @POST("/login")
    fun login(@Body user: User) : Call<LoginResult>


    // 스플래시에서 토큰 권한정보 확인시
    @GET("/admin/get")
    fun adminGet(@Query("token") token: String): Call<TokenGet>

}