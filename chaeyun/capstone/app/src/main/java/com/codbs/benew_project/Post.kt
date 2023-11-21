package com.codbs.benew_project

import com.google.gson.annotations.SerializedName

public class Post (
    @SerializedName(value="userId") var userId:Int,
    @SerializedName(value="id") var id:Int,
    @SerializedName(value="title") var title:String,
    @SerializedName(value="completed") var completed:Boolean = false
)