package com.gmlab.team_benew.Chat_chaeyun

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getRetrofit():Retrofit{
    val retrofit=Retrofit.Builder()
        .baseUrl("http://ec2-3-39-251-72.ap-northeast-2.compute.amazonaws.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit
}