package com.example.uzumbankapp.data.model

import com.google.gson.annotations.SerializedName

data class SignUpData(
    val phone: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val bornDate: String,
    val gender: String
)