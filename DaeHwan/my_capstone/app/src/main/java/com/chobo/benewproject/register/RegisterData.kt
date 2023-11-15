package com.chobo.benewproject.register

import com.google.gson.annotations.SerializedName

data class RegisterData (
    @SerializedName("account")
    private var account: String,

    @SerializedName("password")
    private var password: String,

    @SerializedName("name")
    private var name: String,

    @SerializedName("gender")
    private var gender: String,

    @SerializedName("birthday")
    private var birthday: String,

    @SerializedName("email")
    private var email: String,

    @SerializedName("major")
    private var major: String,

    @SerializedName("phoneNumber")
    private var phoneNumber: String
){
    override fun toString() : String{
        return "account=$account, password=$password, name=$name, gender=$gender, " +
                "birthday=$birthday, email=$email, major=$major, phoneNumber=$phoneNumber"
    }
}