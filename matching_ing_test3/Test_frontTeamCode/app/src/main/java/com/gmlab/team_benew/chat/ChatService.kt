package com.gmlab.team_benew.chat

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.gmlab.team_benew.Chat_chaeyun.Interface
import com.gmlab.team_benew.Chat_chaeyun.getRetrofit
import com.gmlab.team_benew.main.HomeFragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatService(private val context: ChatListFragment) {
    private var chatRoomNameCallback:ChatRoomNameCallback?=null

    fun setchatRoomNameCallback(callback:ChatRoomNameCallback){
        this.chatRoomNameCallback=callback
    }

    //인터페이스 객체 생성
    var networkService: ChatInterface = getRetrofit().create(ChatInterface::class.java)


    //userid이용해서 roomname갖고와서 chatlistfrag에 표시하기
    fun createChatRoom(user1:Int){
        networkService.getChatList(user1).enqueue(object: Callback<ChatResponse> {
            override fun onResponse(call: Call<ChatResponse?>, response: Response<ChatResponse?>) {

                val response1=response.body()



                when(response.code()){
                    200->{
                        val name=response1?.chatRoomName
                        chatRoomNameCallback?.ChatRoomNameReceived(name ?: "")

                    }
                       //성공이니까 responsebody불러서 레이아웃에 띄워야함
                    else->{
                        response.code()
                    }
                }

            }

            override fun onFailure(call: Call<ChatResponse?>, t: Throwable) {
                Log.d("fail","네트워크 연결실패")
            }

        })
    }




}

