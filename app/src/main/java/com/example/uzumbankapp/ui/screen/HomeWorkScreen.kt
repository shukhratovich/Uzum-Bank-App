package com.example.uzumbankapp.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.uzumbankapp.R
import com.example.uzumbankapp.core.utils.LocationHelper
import com.example.uzumbankapp.databinding.ScreenHomeWorkBinding
import com.example.uzumbankapp.databinding.ScreenIntroBinding

class HomeWorkScreen : Fragment() {
    private var _binding: ScreenHomeWorkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenHomeWorkBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUzbek.setOnClickListener {
            LocationHelper.setLocation(requireContext(), "uz")
            findNavController().navigate(HomeWorkScreenDirections.actionHomeWorkScreenToSignInScreen())
        }

        binding.buttonRussian.setOnClickListener {
            LocationHelper.setLocation(requireContext(), "ru")
            findNavController().navigate(HomeWorkScreenDirections.actionHomeWorkScreenToSignInScreen())

        }

        binding.buttonEnglish.setOnClickListener {
            LocationHelper.setLocation(requireContext(), "en")
            findNavController().navigate(HomeWorkScreenDirections.actionHomeWorkScreenToSignInScreen())
        }

        binding.buttonDark.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            findNavController().navigate(HomeWorkScreenDirections.actionHomeWorkScreenToSignInScreen())
        }

        binding.buttonLight.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            findNavController().navigate(HomeWorkScreenDirections.actionHomeWorkScreenToSignInScreen())
        }
    }
}