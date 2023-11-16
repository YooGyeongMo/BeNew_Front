package com.gmlab.team_benew.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName (value = "code") val code: Int,
    @SerializedName (value = "description") val description: String,
    @SerializedName (value = "isSuccess") val isSuccess: Boolean,
    @SerializedName(value = "result") val result: Result?
)
data class Result(
    @SerializedName (value ="id") var id : Int,
    @SerializedName (value = "token") var token : String
)