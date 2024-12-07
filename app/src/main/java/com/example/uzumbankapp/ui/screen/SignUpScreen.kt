package com.example.uzumbankapp.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.uzumbankapp.data.model.SignUpData
import com.example.uzumbankapp.databinding.ScreenSignUpBinding
import com.example.uzumbankapp.presenters.SignUpViewModel
import com.example.uzumbankapp.presenters.viewmodel.SignUpViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
// UI ga token kelmaydi

@AndroidEntryPoint
class SignUpScreen : Fragment() {
    private val viewModel: SignUpViewModel by viewModels<SignUpViewModelImpl>()
    private var _binding: ScreenSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openVerifyScreenLiveData.observe(this) {
            findNavController().navigate(
                SignUpScreenDirections.actionSignUpScreenToVerifyScreen(it)
            )
        }
        viewModel.popUpBackStackLiveData.observe(this) { findNavController().popBackStack() }
        viewModel.firstNameLiveData.observe(this) {}
        viewModel.lastNameLiveData.observe(this) {}

        viewModel.genderLiveData.observe(this) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var phone = binding.phone.text.toString()
        phone = phone.replace(" ", "").replace("-", "")
        val password = binding.password.text.toString()
        val firstname = binding.firstname.text.toString()
        val lastname = binding.lastname.text.toString()
        val bornDate = binding.bornDate.text.toString()
        val gender = binding.gender.text.toString()
        binding.btnContinue.setOnClickListener {
            viewModel.signUpClicked(
                SignUpData(
                    phone = phone,
                    password = password,
                    firstName = firstname,
                    lastName = lastname,
                    bornDate = bornDate,
                    gender = gender
                )
            )
        }
    }
}