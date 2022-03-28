package com.example.ahha_android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentPlantExchangeBinding

class PlantExchangeFragment : Fragment() {
    private lateinit var binding: FragmentPlantExchangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentPlantExchangeBinding.inflate(inflater, container, false)



        return binding.root
    }


}