package com.example.uzumbankapp.presenters

import androidx.lifecycle.LiveData

interface SignInViewModel {
    val progressLiveData:LiveData<Boolean>
    val openSignUp: LiveData<Unit>
    val openVerifySignIn: LiveData<String>
    val errorPhone: LiveData<String>
    val errorPassword: LiveData<String>
    val errorMessage:LiveData<String>

    fun signUpClicked()
    fun continueClicked(phone: String, password: String)
}