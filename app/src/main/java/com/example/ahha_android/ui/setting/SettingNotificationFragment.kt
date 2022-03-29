package com.example.ahha_android.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSettingNotificationBinding
import com.example.ahha_android.ui.viewmodel.SettingViewModel
import com.example.ahha_android.util.setStatusBarColor
import kotlin.properties.Delegates

class SettingNotificationFragment : Fragment() {
    private lateinit var binding: FragmentSettingNotificationBinding
    private val viewModel: SettingViewModel by viewModels()
    private var notificationLimit by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingNotificationBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setStatusBarColor(requireActivity(), R.color.white)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNotificationOption()
    }

    private fun getNotificationOption(){
        val notificationOn = arguments?.getString("notificationOn")

        if(binding.switchPushNotification.isChecked){
            notificationLimit > 0
        }else{
            notificationLimit = 0
        }

        if (notificationOn != null) {
            viewModel.changeNotificationSetting(notificationOn, notificationLimit)
        }
    }
}