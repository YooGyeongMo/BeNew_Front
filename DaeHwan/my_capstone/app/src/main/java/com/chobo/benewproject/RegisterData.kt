package com.chobo.benewproject

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

    @SerializedName("birthdate")
    private var birthdate: String,

    @SerializedName("email")
    private var email: String,

    @SerializedName("major")
    private var major: String,

    @SerializedName("phoneNumber")
    private var phoneNumber: String
){
    override fun toString() : String{
        return "account=$account, password=$password, name=$name, gender=$gender," +
                "birthdate=$birthdate, email=$email, major=$major, phoneNumber=$phoneNumber"
    }
}