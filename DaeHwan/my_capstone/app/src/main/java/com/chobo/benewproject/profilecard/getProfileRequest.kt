package com.chobo.benewproject.profilecard

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface getProfileRequest {
        @GET("profile/{memberId}")
        fun getProfile(
            @Header("Authorization") token: String,
            @Path("memberId") memberId: String
        ): Call<getProfileData>
}