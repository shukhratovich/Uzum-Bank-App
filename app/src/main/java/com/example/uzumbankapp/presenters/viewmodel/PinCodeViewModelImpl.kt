package com.example.uzumbankapp.presenters.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.uzumbankapp.data.source.local.pref.SharedPref
import com.example.uzumbankapp.presenters.PinCodeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModelImpl @Inject constructor(
    private val pref: SharedPref
) : ViewModel(), PinCodeViewModel {
    override val openPinVerifyLiveData = MutableLiveData<String>()
    override val openHomeLiveData = MutableLiveData<Unit>()
    override val pinErrorLiveData = MutableLiveData<String>()

    override fun checkPinCode(pin: String) {
        if (pref.pin.isEmpty()) openPinVerifyLiveData.value = pin
        else if (pref.pin.contains(pin)) openHomeLiveData.value = Unit
        else pinErrorLiveData.value = "Pin noto`g`ri"
    }
}