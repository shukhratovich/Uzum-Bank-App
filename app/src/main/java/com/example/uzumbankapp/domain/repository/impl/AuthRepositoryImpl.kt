package com.example.uzumbankapp.domain.repository.impl

import android.util.Log
import com.example.uzumbankapp.data.source.remote.api.auth.AuthApi
import com.example.uzumbankapp.data.source.remote.request.ResendTokenRequest
import com.example.uzumbankapp.data.source.remote.request.SignInRequest
import com.example.uzumbankapp.data.source.remote.request.SignUpRequest
import com.example.uzumbankapp.data.source.remote.request.UpdateTokenRequest
import com.example.uzumbankapp.data.source.remote.request.VerifyRequest
import com.example.uzumbankapp.data.source.remote.response.MessageResponse
import com.example.uzumbankapp.data.source.remote.response.TokenResponse
import com.example.uzumbankapp.data.source.remote.response.VerifyResponse
import com.example.uzumbankapp.domain.repository.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val gson: Gson
) : AuthRepository {
    override fun registerUser(signUpRequest: SignUpRequest) = flow {
        Log.d("TTT", "registerUser: ")
        val result = authApi.registerUser(signUpRequest)

        if (result.isSuccessful) {
            Log.d("TTT", "registerUser: true")
            emit(Result.success(result.body()!!))
        }
    }.flowOn(Dispatchers.IO).catch { error ->
        Log.d("TTT", "registerUser: error")

        emit(Result.failure(error))
    }

    override fun verifyRegister(verifyRequest: VerifyRequest): Flow<Result<VerifyResponse>> =
        performApiCall(verifyRequest) { authApi.registerVerify(it) }

    override fun resendRegisterToken(resendTokenRequest: ResendTokenRequest) =
        performApiCall(resendTokenRequest) { authApi.registerResend(it) }

    override fun loginUser(signInRequest: SignInRequest): Flow<Result<TokenResponse>> =
        performApiCall(signInRequest) { authApi.loginUser(it) }

    override fun verifyLogin(verifyRequest: VerifyRequest): Flow<Result<VerifyResponse>> =
        performApiCall(verifyRequest) { authApi.loginVerify(it) }

    override fun resendLoginToken(resendTokenRequest: ResendTokenRequest): Flow<Result<TokenResponse>> =
        performApiCall(resendTokenRequest) { authApi.loginResend(it) }

    override fun updateToken(updateTokenRequest: UpdateTokenRequest): Flow<Result<VerifyResponse>> =
        performApiCall(updateTokenRequest) { authApi.updateToken(it) }


    private inline fun <T, R> performApiCall(
        request: T,
        crossinline apiCall: suspend (T) -> retrofit2.Response<R>
    ): Flow<Result<R>> = flow {
        val result = apiCall(request)
        if (result.isSuccessful) {
            emit(Result.success(result.body()!!))
        } else if (result.errorBody() != null) {
            val errorBody = gson.fromJson(result.errorBody()?.string(), MessageResponse::class.java)
            emit(Result.failure(Throwable(errorBody.message)))
        }
    }.flowOn(Dispatchers.IO).catch { error ->
        emit(Result.failure(error))
    }
}