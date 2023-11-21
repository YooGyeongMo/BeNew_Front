package com.codbs.benew_project.Retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TodoService {
    @POST("/todos")
    fun postData(@Body postData: Json): Call<Json>

}