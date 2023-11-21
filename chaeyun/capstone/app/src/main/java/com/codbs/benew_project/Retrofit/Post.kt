package com.codbs.benew_project.Retrofit

import com.google.gson.annotations.SerializedName
//응답값
//코틀린은 데이터클래스 게터/세터 자동으로 구현해줌
//SerializedName은 json에 있는 key값과 동일한 값으로 매칭시켜준다는 의미
data class Post(//u,i,t,c
    @SerializedName("userId") val userId:Int,//Json key가 ""안의 userId를 코틀린상의 userId에 대입된다
    //이 어노테이션이 json을 자바클래스로 변경해주는 gson의 핵심
    @SerializedName("id") val id:Int,
    @SerializedName("title") val title:String,
    @SerializedName("content") val body:String
)
