package com.example.uzumbankapp.data.source.remote.api.auth

import com.example.uzumbankapp.data.source.remote.request.ResendTokenRequest
import com.example.uzumbankapp.data.source.remote.request.SignInRequest
import com.example.uzumbankapp.data.source.remote.request.SignUpRequest
import com.example.uzumbankapp.data.source.remote.request.UpdateTokenRequest
import com.example.uzumbankapp.data.source.remote.request.VerifyRequest
import com.example.uzumbankapp.data.source.remote.response.MessageResponse
import com.example.uzumbankapp.data.source.remote.response.TokenResponse
import com.example.uzumbankapp.data.source.remote.response.VerifyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/sign-up")
    suspend fun registerUser(@Body request: SignUpRequest): Response<TokenResponse>

    @POST("auth/sign-up/verify")
    suspend fun registerVerify(@Body request: VerifyRequest): Response<VerifyResponse>

    @POST("auth/sign-in")
    suspend fun loginUser(@Body request: SignInRequest): Response<TokenResponse>

    @POST("auth/sign-in/verify")
    suspend fun loginVerify(@Body request: VerifyRequest): Response<VerifyResponse>

    @POST("auth/update-token")
    suspend fun updateToken(@Body request: UpdateTokenRequest): Response<VerifyResponse>

    @POST("sign-in/resend")
    suspend fun loginResend(@Body request: ResendTokenRequest): Response<TokenResponse>

    @POST("auth/sign-up/resend")
    suspend fun registerResend(@Body request: ResendTokenRequest): Response<TokenResponse>

    // delete account
    @POST("auth/sign-out")
    suspend fun signOut(@Header("token") token: String): Response<MessageResponse>


}