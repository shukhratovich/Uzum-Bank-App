package com.example.uzumbankapp.presenters

import androidx.lifecycle.LiveData

interface SplashViewModel {
    val timerLiveData: LiveData<Unit>

    fun startTimer()
}