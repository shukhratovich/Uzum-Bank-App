package com.example.uzumbankapp.core.utils

import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppNavigationDispatcher @Inject constructor() : AppNavigator, AppNavigatorHandler {
    override val navigationStack = MutableSharedFlow<NavigationArgs>()

    override suspend fun navigateTo(resId: Int) {
        navigationStack.emit { navigate(resId) }
    }

    override suspend fun navigateTo(dir: NavDirections) {
        navigationStack.emit { navigate(dir) }
    }

    override suspend fun goBack() {
        navigationStack.emit { navigateUp() }
    }

}