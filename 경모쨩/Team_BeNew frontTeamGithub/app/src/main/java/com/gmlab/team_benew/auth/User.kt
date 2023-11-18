package com.gmlab.team_benew.auth

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class User(
    @SerializedName(value = "account") var id : String,
    @SerializedName(value = "password") var password : String,
)

