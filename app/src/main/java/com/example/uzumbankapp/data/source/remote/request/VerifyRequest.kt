package com.example.uzumbankapp.data.source.remote.request

data class VerifyRequest(
    val token: String,
    val code: String
)