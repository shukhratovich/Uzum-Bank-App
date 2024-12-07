package com.example.uzumbankapp.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.uzumbankapp.databinding.ScreenSplashBinding
import com.example.uzumbankapp.presenters.SplashViewModel
import com.example.uzumbankapp.presenters.viewmodel.SplashViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment() {
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    private var _binding: ScreenSplashBinding? = null
    private val binding get() = _binding!!
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openSignInScreenLiveData.observe(this) {
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToSignInScreen())
        }
        viewModel.openPinCodeScreenLiveData.observe(this) {
            findNavController().navigate(SplashScreenDirections.actionSplashScreenToPinCodeScreen())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.startTimer()
    }


}