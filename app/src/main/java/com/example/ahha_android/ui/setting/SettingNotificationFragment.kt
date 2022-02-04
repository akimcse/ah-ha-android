package com.example.ahha_android.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ahha_android.databinding.FragmentSettingNotificationBinding

class SettingNotificationFragment : Fragment() {
    private lateinit var binding: FragmentSettingNotificationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }
}