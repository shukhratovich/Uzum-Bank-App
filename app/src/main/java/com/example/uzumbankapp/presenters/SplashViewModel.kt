package com.example.uzumbankapp.presenters

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val openPinCodeScreenLiveData: LiveData<Unit>
    val openSignInScreenLiveData: LiveData<Unit>

    fun startTimer()
}