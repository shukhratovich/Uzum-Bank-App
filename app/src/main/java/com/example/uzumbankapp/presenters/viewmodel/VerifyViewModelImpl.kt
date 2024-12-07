package com.example.uzumbankapp.presenters.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uzumbankapp.data.model.AppMapper.toRequest
import com.example.uzumbankapp.data.model.VerifyData
import com.example.uzumbankapp.data.source.local.pref.SharedPref
import com.example.uzumbankapp.data.source.remote.request.ResendTokenRequest
import com.example.uzumbankapp.domain.repository.AuthRepository
import com.example.uzumbankapp.presenters.VerifyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VerifyViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val pref: SharedPref
) : ViewModel(), VerifyViewModel {
    override val openHomeTestLiveData = MutableLiveData<Unit>()

    override fun signUpResend(token: String) {
        authRepository.resendRegisterToken(ResendTokenRequest(token))
    }

    override fun signInResend(token: String) {
        authRepository.resendLoginToken(ResendTokenRequest(token))
            .onEach {
                Log.d("TTT", "signInResend: onEach")
                it.onSuccess { tokenResponse -> pref.token = tokenResponse.token }
                it.onFailure { error -> }
            }
            .launchIn(viewModelScope)
    }

    override fun signInVerify(code: String) {

        if (code.length == 6) {
            authRepository.verifyLogin(VerifyData(pref.token, code).toRequest())
                .onEach {
                    it.onSuccess { openHomeTestLiveData.value = Unit }
                    it.onFailure { }
                }
                .launchIn(viewModelScope)
        }
    }

    override fun signUpVerify(code: String) {

    }
}