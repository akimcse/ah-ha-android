package com.example.ahha_android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentMainBinding
import com.example.ahha_android.ui.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        addObserver()

        return binding.root
    }

    private fun addObserver() {
        viewModel.ordinalNumber.observe(viewLifecycleOwner) {
            binding.textViewPlantNumber.text = getString(R.string.main_plant_number_format, it)
        }

        viewModel.mailCount.observe(viewLifecycleOwner) {
            binding.textViewMailCount.text = getString(R.string.main_mail_count_format, it)
        }
    }
}