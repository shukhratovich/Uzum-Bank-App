package com.example.uzumbankapp.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class VerifyResponse(
    @SerializedName("refresh-token")
    val refreshToken: String,
    @SerializedName("access-token")
    val accessToken: String
)