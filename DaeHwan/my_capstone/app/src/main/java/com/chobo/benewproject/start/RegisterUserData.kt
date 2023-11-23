package com.chobo.benewproject.start

import com.google.gson.annotations.SerializedName

data class RegisterUserData(
    var account: String,

    var password: String,

    var name: String,

    var gender: String,

    var birthday: String,

    var email: String,

    var major: String,

    var phoneNumber: String
    )
