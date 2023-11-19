package com.gmlab.team_benew.auth

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName (value = "code") val code: Int,
    @SerializedName (value = "description") val description: String,
    @SerializedName (value = "isSuccess") val isSuccess: Boolean,
)

// 로그인 응답값
data class LoginResult(
    @SerializedName("account") var account: String,
    @SerializedName("birthday") var birthday: String,
    @SerializedName("email") var email: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("id") var id: Int,
    @SerializedName("major") var major: String,
    @SerializedName("name") var name: String,
    @SerializedName("password") var password: String,
    @SerializedName("phoneNumber") var phoneNumber: String,
    @SerializedName("roles") var roles: List<Role>,
    @SerializedName("token") var token: String
)

data class TokenGet(
    @SerializedName("account") var account: String,
    @SerializedName("birthday") var birthday: String,
    @SerializedName("email") var email: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("id") var id: Int,
    @SerializedName("major") var major: String,
    @SerializedName("name") var name: String,
    @SerializedName("password") var password: String,
    @SerializedName("phoneNumber") var phoneNumber: String,
    @SerializedName("roles") var roles: List<Role>,
    @SerializedName("token") var token: String
)

data class Role(
    @SerializedName("name") var name: String
)