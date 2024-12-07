package com.example.uzumbankapp.presenters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uzumbankapp.data.model.AppMapper.toRequest
import com.example.uzumbankapp.data.model.SignInData
import com.example.uzumbankapp.data.source.local.pref.SharedPref
import com.example.uzumbankapp.domain.repository.AuthRepository
import com.example.uzumbankapp.presenters.SignInViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignInViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val pref: SharedPref
) : ViewModel(), SignInViewModel {
    override val progressLiveData = MutableLiveData<Boolean>()
    override val openSignUp = MutableLiveData<Unit>()
    override val openVerifySignIn = MutableLiveData<String>()
    override val errorPhone = MutableLiveData<String>()
    override val errorPassword = MutableLiveData<String>()
    override val errorMessage = MutableLiveData<String>()


    override fun signUpClicked() {
        openSignUp.value = Unit
    }

    override fun continueClicked(phone: String, password: String) {
        if (phone.isEmpty()) {
            errorPhone.value = "Telefon raqamini kiriting!"
        } else if (password.isEmpty() || password.length < 6) {
            errorPassword.value = "Parol 6 ta belgidan kam bo`lmasligi kerak!"
        } else {
            progressLiveData.value = true
            authRepository.loginUser(SignInData(phone, password).toRequest())
                .onEach {
                    delay(1000)
                    it.onSuccess { tokenResponse ->
                        pref.token = tokenResponse.token
                        openVerifySignIn.value = tokenResponse.token
                    }
                    it.onFailure { error ->
                        errorMessage.value = error.message
                    }
                    progressLiveData.value = false
                }
                .launchIn(viewModelScope)
        }
    }
}