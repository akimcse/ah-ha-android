package com.example.ahha_android.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSettingBinding
import com.example.ahha_android.ui.viewmodel.SettingViewModel
import com.example.ahha_android.util.setStatusBarColor
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by activityViewModels()
    private lateinit var navController: NavController
    private var notificationOn: Boolean = true
    private var notificationString: String = "YES"
    private var notificationLimit by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        setStatusBarColor(requireActivity(), R.color.white)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        setNotificationOption()
        getNotificationOption()
    }

    private fun setNotificationOption() {
        lifecycleScope.launch {
            viewModel.apply {
                fetchUser()
            }
        }
    }

    private fun getNotificationOption() {
        binding.switchInfoNotification.setOnClickListener {
            notificationOn = !notificationOn

            if (notificationOn) {
                notificationString = "YES"
                sendToServer()
            } else {
                notificationString = "NO"
                sendToServer()
            }
        }
    }

    private fun sendToServer() {
        notificationLimit = viewModel.notificationLimit.value!!

        viewModel.changeNotificationSetting(notificationString, notificationLimit)
        Log.d("*******notificationInfo", notificationString)
        Log.d("******notificationLimit", notificationLimit.toString())
    }
}