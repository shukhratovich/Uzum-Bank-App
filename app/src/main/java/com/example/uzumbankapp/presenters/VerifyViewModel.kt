package com.example.uzumbankapp.presenters

import androidx.lifecycle.LiveData

interface VerifyViewModel {
    val openHomeTestLiveData: LiveData<Unit>

    fun signUpResend(token: String)
    fun signInResend(token: String)
    fun signInVerify(code: String)
    fun signUpVerify(code: String)
}