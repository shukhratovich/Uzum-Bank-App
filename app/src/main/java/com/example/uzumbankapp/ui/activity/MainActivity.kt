package com.example.uzumbankapp.ui.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.uzumbankapp.R
import com.example.uzumbankapp.core.utils.LocationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.example.uzumbankapp.core.utils.AppNavigationDispatcher
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationDispatcher: AppNavigationDispatcher
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val navController =
            findViewById<FragmentContainerView>(R.id.main).getFragment<NavHostFragment>().navController
        navigationDispatcher.navigationStack
            .onEach { it(navController) }
            .launchIn(lifecycleScope)
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocationHelper.attach(newBase))
    }
}