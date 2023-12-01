package com.gmlab.team_benew.chat

import com.google.gson.annotations.SerializedName

//name,roomid
data class ChatResponse(
    @SerializedName("name") var chatRoomName:String,
    @SerializedName("roomId") var roomId:String


)
