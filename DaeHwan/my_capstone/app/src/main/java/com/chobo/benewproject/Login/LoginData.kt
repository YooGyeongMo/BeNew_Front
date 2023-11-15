package com.chobo.benewproject.Login

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("account")
    private var account: String,

    @SerializedName("password")
    private var password: String,
) {
    override fun toString() : String{
        return "account=$account, password=$password"
    }
}

data class JwtResponse(
    @SerializedName("token")
    val token: String
)