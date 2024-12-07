package com.example.uzumbankapp.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.uzumbankapp.R
import com.example.uzumbankapp.databinding.ScreenVerifyBinding
import com.example.uzumbankapp.presenters.VerifyViewModel
import com.example.uzumbankapp.presenters.viewmodel.VerifyViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class VerifyScreen : Fragment() {
    private val viewModel: VerifyViewModel by viewModels<VerifyViewModelImpl>()
    private var _binding: ScreenVerifyBinding? = null
    private val binding get() = _binding!!
    private var previousBackStackEntry: NavBackStackEntry? = null
    private val args: VerifyScreenArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenVerifyBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        previousBackStackEntry = findNavController().previousBackStackEntry
        viewModel.openHomeTestLiveData.observe(this) {
            findNavController().navigate(VerifyScreenDirections.actionVerifyScreenToPinCodeScreen())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener { findNavController().popBackStack() }
        when (previousBackStackEntry?.destination?.id) {
            R.id.signInScreen -> {
                binding.tvResent.setOnClickListener { viewModel.signInResend(args.token) }
                binding.btnContinue.setOnClickListener {
                    viewModel.signInVerify(binding.otpVerify.otp.toString())
                }
            }

            R.id.signUpScreen -> {
                binding.tvResent.setOnClickListener { viewModel.signUpResend(args.token) }

            }

        }
    }
}