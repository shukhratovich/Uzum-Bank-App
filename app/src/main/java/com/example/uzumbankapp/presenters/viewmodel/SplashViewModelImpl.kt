package com.example.uzumbankapp.presenters.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uzumbankapp.data.source.local.pref.SharedPref
import com.example.uzumbankapp.presenters.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val pref: SharedPref
) : ViewModel(), SplashViewModel {
    override val openPinCodeScreenLiveData = MutableLiveData<Unit>()
    override val openSignInScreenLiveData = MutableLiveData<Unit>()

    override fun startTimer() {
        viewModelScope.launch {
            delay(10)
            if (pref.refreshToken.isEmpty()) openSignInScreenLiveData.value = Unit
            else openPinCodeScreenLiveData.value = Unit
        }
    }

}