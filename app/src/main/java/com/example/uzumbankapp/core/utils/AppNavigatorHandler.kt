package com.example.uzumbankapp.core.utils

import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

typealias NavigationArgs = NavController.()-> Unit

interface AppNavigatorHandler {
    val navigationStack: Flow<NavigationArgs>
}