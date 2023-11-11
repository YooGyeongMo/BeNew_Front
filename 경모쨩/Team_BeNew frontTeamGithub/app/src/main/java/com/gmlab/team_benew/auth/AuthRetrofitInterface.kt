package com.gmlab.team_benew.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/register")
    fun signUp(@Body user: User) : Call<AuthResponse>
}