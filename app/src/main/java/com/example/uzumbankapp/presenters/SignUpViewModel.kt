package com.example.uzumbankapp.presenters

import androidx.lifecycle.LiveData
import com.example.uzumbankapp.data.model.SignUpData

interface SignUpViewModel {
    val errorPhoneLiveData:LiveData<String>
    val firstNameLiveData:LiveData<String>
    val lastNameLiveData:LiveData<String>
    val bornDateLiveData:LiveData<String>
    val genderLiveData:LiveData<String>
    val passwordLiveData:LiveData<String>
    val errorMessageLiveData: LiveData<String>
    val openVerifyScreenLiveData: LiveData<String>
    val popUpBackStackLiveData:LiveData<Unit>

    fun signUpClicked(signUpData: SignUpData)
    fun backClicked()
}