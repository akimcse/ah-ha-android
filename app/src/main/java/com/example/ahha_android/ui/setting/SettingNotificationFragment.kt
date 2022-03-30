package com.example.ahha_android.ui.setting

import android.os.Bundle
import android.util.Log
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
    var notificationOn: Boolean = true
    var notificationLimit = 1

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
        initClickListener()
    }

    private fun getNotificationOption() {
        viewModel.notificationCount.observe(viewLifecycleOwner) {
            notificationLimit = it
        }

        binding.switchPushNotification.setOnClickListener {
            notificationOn = !notificationOn

            if (notificationOn) {
                viewModel.notificationCount.observe(viewLifecycleOwner) {
                    notificationLimit = it
                }
                Log.d("***************On", notificationLimit.toString())
            } else {
                notificationLimit = 0
                Log.d("***************Off", notificationLimit.toString())
            }
        }
    }

    private fun initClickListener() {
        val notificationString = arguments?.getString("notificationString")

        binding.textViewComplete.setOnClickListener {
            Log.d("***********************", "send to server")
            Log.d("*********notificationOn", notificationString.toString())
            Log.d("******notificationLimit", notificationLimit.toString())

            if (notificationString != null) {
                viewModel.changeNotificationSetting(notificationString, notificationLimit)
            } else {
                Log.d("**setNotificationOption", "failed")
            }
        }
    }
}