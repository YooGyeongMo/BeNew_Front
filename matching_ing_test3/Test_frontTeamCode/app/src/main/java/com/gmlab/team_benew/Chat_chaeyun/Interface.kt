package com.gmlab.team_benew.Chat_chaeyun

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface Interface {
    //채팅방 생성
    @POST("/chat/new/{user1}/{user2}")
    fun createChatRoom(
        @Path("user1") user1:Int,
        @Path("user2") user2:Int
    ):Call<ChatModel>



    //알람 생성
    @POST("/alarms")
    fun createAlarm(
        @Body alarm:AlarmsModel
    ):Call<AlarmsModel>

}