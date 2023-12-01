package com.gmlab.team_benew.chat

import com.google.gson.annotations.SerializedName

data class ChatModel(

    @SerializedName("name") val chatRoomName:String,
    val roomId:String

)
