package com.gmlab.team_benew.Chat_chaeyun

import com.google.gson.annotations.SerializedName

data class ChatModel(

    @SerializedName("ChatName") val name:String,
    val roomId:String

)
