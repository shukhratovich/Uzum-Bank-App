package com.example.uzumbankapp.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class UpdateTokenRequest(
    @SerializedName("refresh-token")
    val refreshToken:String
)
