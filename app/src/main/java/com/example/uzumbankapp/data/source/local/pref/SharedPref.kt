package com.example.uzumbankapp.data.source.local.pref

import android.content.Context
import android.content.SharedPreferences
import com.example.uzumbankapp.core.utils.SharedPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor(@ApplicationContext context: Context) :
    SharedPreference(context) {
    var accessToken: String by strings()
    var refreshToken: String by strings()
    var token: String by strings()
    var pin: String by strings()
}