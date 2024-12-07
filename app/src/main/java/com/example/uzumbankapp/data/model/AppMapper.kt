package com.example.uzumbankapp.data.model

import com.example.uzumbankapp.data.source.remote.request.SignInRequest
import com.example.uzumbankapp.data.source.remote.request.SignUpRequest
import com.example.uzumbankapp.data.source.remote.request.VerifyRequest

object AppMapper {
    fun SignInData.toRequest() = SignInRequest(this.phone, this.password)

    fun SignUpData.toRequest() =
        SignUpRequest(
            phone = this.phone,
            password = this.password,
            firstName = this.firstName,
            lastName = this.lastName,
            bornDate = this.bornDate,
            gender = this.gender
        )

    fun VerifyData.toRequest() = VerifyRequest(token = this.token, code = this.code)
}