package com.codbs.benew_project.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//이걸로 post통신함
object RetrofitBuilder {
    var api:TodoService=Retrofit.Builder()
        .baseUrl("http://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TodoService::class.java)

}

