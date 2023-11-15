package com.chobo.benewproject.Login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRequest {
    @POST("login")
    fun login(@Body request: LoginData): Call<JwtResponse>
}