package com.example.uzumbankapp.core.utils

import androidx.navigation.NavDirections

interface AppNavigator {
    suspend fun navigateTo(resId:Int)
    suspend fun navigateTo(dir: NavDirections)
    suspend fun goBack()
}


