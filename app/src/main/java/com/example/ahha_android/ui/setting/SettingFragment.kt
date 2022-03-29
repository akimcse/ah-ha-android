package com.example.ahha_android.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSettingBinding
import com.example.ahha_android.util.setStatusBarColor

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        setStatusBarColor(requireActivity(), R.color.white)
        return binding.root
    }
}