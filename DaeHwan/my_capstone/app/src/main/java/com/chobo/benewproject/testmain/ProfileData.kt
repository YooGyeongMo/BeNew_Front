package com.chobo.benewproject.testmain

import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("nickname")
    val nickname : String
)
