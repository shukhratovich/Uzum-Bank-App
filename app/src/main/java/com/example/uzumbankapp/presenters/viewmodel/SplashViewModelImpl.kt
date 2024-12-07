package com.example.uzumbankapp.presenters.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uzumbankapp.presenters.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor() : ViewModel(), SplashViewModel {
    override val timerLiveData = MutableLiveData<Unit>()

    override fun startTimer() {
        viewModelScope.launch {
            delay(10)
            timerLiveData.value = Unit
        }
    }

}