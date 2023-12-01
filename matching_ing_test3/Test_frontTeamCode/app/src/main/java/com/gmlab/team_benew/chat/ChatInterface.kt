package com.gmlab.team_benew.chat

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChatInterface {
    @GET("/chat/rooms/{userId}")
    fun getChatList(@Path("userId") userId:Int): Call<ChatResponse>
}