package com.example.uzumbankapp.domain.repository

import com.example.uzumbankapp.data.source.remote.request.ResendTokenRequest
import com.example.uzumbankapp.data.source.remote.request.SignInRequest
import com.example.uzumbankapp.data.source.remote.request.SignUpRequest
import com.example.uzumbankapp.data.source.remote.request.UpdateTokenRequest
import com.example.uzumbankapp.data.source.remote.request.VerifyRequest
import com.example.uzumbankapp.data.source.remote.response.TokenResponse
import com.example.uzumbankapp.data.source.remote.response.VerifyResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun registerUser(signUpRequest: SignUpRequest): Flow<Result<TokenResponse>>
    fun verifyRegister(verifyRequest: VerifyRequest): Flow<Result<VerifyResponse>>
    fun resendRegisterToken(resendTokenRequest: ResendTokenRequest): Flow<Result<TokenResponse>>
    fun loginUser(signInRequest: SignInRequest): Flow<Result<TokenResponse>>
    fun verifyLogin(verifyRequest: VerifyRequest): Flow<Result<VerifyResponse>>
    fun resendLoginToken(resendTokenRequest: ResendTokenRequest): Flow<Result<TokenResponse>>
    fun updateToken(updateTokenRequest: UpdateTokenRequest):Flow<Result<VerifyResponse>>
}