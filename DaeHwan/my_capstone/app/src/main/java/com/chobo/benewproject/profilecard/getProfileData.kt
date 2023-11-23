package com.chobo.benewproject.profilecard

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.google.gson.annotations.SerializedName

data class getProfileData(
    @SerializedName("instruction")
    val instruction: String,
    @SerializedName("member")
    val member: Member,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("personalLink")
    val personalLink: String,
    @SerializedName("projectExperience")
    val projectExperience: Boolean,
    @SerializedName("role")
    val role: String
)

data class Member(
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("account")
    val major: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String
)
