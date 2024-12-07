package com.example.uzumbankapp.presenters.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uzumbankapp.data.model.AppMapper.toRequest
import com.example.uzumbankapp.data.model.SignUpData
import com.example.uzumbankapp.domain.repository.AuthRepository
import com.example.uzumbankapp.presenters.SignUpViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(), SignUpViewModel {
    override val openVerifyScreenLiveData = MutableLiveData<String>()
    override val errorPhoneLiveData = MutableLiveData<String>()
    override val firstNameLiveData = MutableLiveData<String>()
    override val lastNameLiveData = MutableLiveData<String>()
    override val bornDateLiveData = MutableLiveData<String>()
    override val genderLiveData = MutableLiveData<String>()
    override val passwordLiveData = MutableLiveData<String>()
    override val errorMessageLiveData = MutableLiveData<String>()
    override val popUpBackStackLiveData = MutableLiveData<Unit>()


    override fun signUpClicked(signUpData: SignUpData) {
        if (signUpData.phone.isEmpty()) {
            errorPhoneLiveData.value = "Maydonni to`ldiring!"
        } else if (signUpData.password.isEmpty() || signUpData.password.length < 6) {
            passwordLiveData.value =
                "Parol 6 tadan kam bo`lmasligi kerak"
        } else if (signUpData.firstName.isEmpty()) {
            firstNameLiveData.value = "Ismingizni kiriting!"
        } else if (signUpData.lastName.isEmpty()) {
            lastNameLiveData.value = "Familiya kiriting!"
        } else if (signUpData.bornDate.isEmpty()) {
            bornDateLiveData.value = "Tug`ilgan sanani kiriting!"
        } else if (signUpData.gender.isEmpty()) {
            genderLiveData.value = "Jinsini tanlang!"
        } else {
            Log.d("TTT", "signUpClicked:repo called")

        }

        authRepository.registerUser(signUpData.toRequest())
            .onEach {
                it.onSuccess { tokenResponse ->
                    openVerifyScreenLiveData.value = tokenResponse.token
                }
                it.onFailure {
                    Log.d("TTT", "signUpClicked: $it")
                }
            }
            .launchIn(viewModelScope)

    }

    override fun backClicked() {

    }

}