package com.example.ahha_android.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSettingNotificationBinding
import com.example.ahha_android.ui.viewmodel.SettingViewModel
import com.example.ahha_android.util.setStatusBarColor
import kotlinx.coroutines.launch

class SettingNotificationFragment : Fragment() {
    private lateinit var binding: FragmentSettingNotificationBinding
    private val viewModel: SettingViewModel by activityViewModels()
    var notificationOn: String = "NO"
    private var notificationLimit = 1
    private lateinit var notificationInfo: String

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

        setNotificationOption()
        getNotificationOption()
        sendToServer()

    }

    private fun setNotificationOption() {
        lifecycleScope.launch {
            viewModel.apply {
                fetchUser()
            }
        }

        viewModel.notificationCount.observe(viewLifecycleOwner) {
            notificationLimit = it
        }

        binding.switchPushNotification.isChecked = viewModel.notificationMailOn.value == "YES"
    }

    private fun getNotificationOption() {
        binding.switchPushNotification.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                viewModel.notificationCount.observe(viewLifecycleOwner) {
                    notificationLimit = it
                }
                notificationOn = "YES"

            }else {
                notificationLimit = 0
                notificationOn = "NO"
            }
        }
    }

    private fun sendToServer() {
        notificationInfo = viewModel.notificationInfo.value!!

        binding.textViewComplete.setOnClickListener {
            viewModel.setNotificationMailOn(notificationOn)
            viewModel.changeNotificationSetting(notificationInfo, notificationLimit)
            Toast.makeText(requireContext(), "Modifications Completed!", Toast.LENGTH_SHORT).show()
            Log.d("InfoON", notificationInfo)
            Log.d("MailLimit", notificationLimit.toString())
        }

        viewModel.setNotificationCount()
    }
}