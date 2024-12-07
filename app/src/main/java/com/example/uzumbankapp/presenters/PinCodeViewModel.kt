package com.example.uzumbankapp.presenters

import androidx.lifecycle.LiveData

interface PinCodeViewModel {
    val openPinVerifyLiveData: LiveData<String>
    val openHomeLiveData: LiveData<Unit>
    val pinErrorLiveData: LiveData<String>

    fun checkPinCode(pin: String)
}