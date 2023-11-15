package com.chobo.benewproject.register

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DoRegister{
    @POST("register")
    fun signup(@Body request: RegisterData): Call<Boolean>
}