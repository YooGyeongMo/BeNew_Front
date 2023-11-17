package com.chobo.benewproject.testmain

import android.content.IntentFilter.AuthorityEntry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface testGetAccount {
    @GET("profile/{memberId}")
    fun getProfile(
        @Header("Authorization") token: String,
        @Path("memberId") memberId: String
    ): Call<ProfileData>
}