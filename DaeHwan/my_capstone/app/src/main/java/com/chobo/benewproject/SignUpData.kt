package com.chobo.benewproject

class SignUpData {
    private lateinit var name : String
    private lateinit var id : String
    private lateinit var password : String

    public fun SignupData(name : String, id : String, password : String){
        this.name = name
        this.id = id
        this.password = password
    }
}