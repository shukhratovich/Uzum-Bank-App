package com.example.uzumbankapp.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.uzumbankapp.R
import com.example.uzumbankapp.databinding.ScreenSignInBinding
import com.example.uzumbankapp.presenters.SignInViewModel
import com.example.uzumbankapp.presenters.viewmodel.SignInViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

// UI ga token kelmaydi
@AndroidEntryPoint
class SignInScreen : Fragment() {
    private val viewModel: SignInViewModel by viewModels<SignInViewModelImpl>()
    private var _binding: ScreenSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.progressLiveData.observe(this) { isLoading ->
            if (isLoading) {
                binding.btnContinue.isClickable = false
                binding.btnContinue.text = ""
                binding.progressLayout.visibility = View.VISIBLE
            } else {
                binding.btnContinue.text = "Davom ettirish"
                binding.btnContinue.isClickable = true
                binding.progressLayout.visibility = View.GONE
            }

        }
        viewModel.openSignUp.observe(this) {
            findNavController().navigate(SignInScreenDirections.actionSignInScreenToSignUpScreen())
        }
        viewModel.openVerifySignIn.observe(this) {
            findNavController().navigate(SignInScreenDirections.actionSignInScreenToVerifyScreen(it))
        }
        viewModel.errorPhone.observe(this) {
            binding.phone.error = it
        }
        viewModel.errorPassword.observe(this) {
            binding.password.error = it
        }
        viewModel.errorMessage.observe(this) {
            Toast.makeText(
                requireContext(),
                it,
                Toast.LENGTH_SHORT
            ).show()
            Log.d("TTT", "onCreate: $it")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRegister.setOnClickListener { viewModel.signUpClicked() }
        binding.translate.setOnClickListener { findNavController().navigate(SignInScreenDirections.actionSignInScreenToHomeWorkScreen()) }
        binding.btnContinue.setOnClickListener {
            var phone = binding.phone.text.toString()
            phone = phone.replace(" ", "").replace("-", "")
            val password = binding.password.text.toString()
            viewModel.continueClicked(phone, password)
        }
    }
}