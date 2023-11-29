package com.gmlab.team_benew.Chat_chaeyun

import GlobalData
import android.telecom.Call
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Response

class Network {


    //인터페이스 객체 생성
    var networkService:Interface= getRetrofit().create(Interface::class.java)

    fun createChatRoom(user1:Int,user2:Int){

        //val response=
        //val chatListCall=networkService.createChatRoom("aa","aa")


        networkService.createChatRoom(user1,user2).enqueue(object: Callback<ChatModel>{
            override fun onResponse(
                call:Call<ChatModel>,
                response: Response<ResponseBody>) {

                val response=response.body()
                val name=response.name

                Log.d("POST CHATROOM SUCCESS",response.toString())
                when(response.code()){
                    200->
                    val name=//성공이니까 responsebody불러서 레이아웃에 띄워야함
                    else->
                }

            }

        })
    }


}