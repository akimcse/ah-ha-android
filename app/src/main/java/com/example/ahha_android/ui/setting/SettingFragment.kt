package com.example.ahha_android.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSettingBinding
import com.example.ahha_android.ui.viewmodel.EditPlantViewModel
import com.example.ahha_android.ui.viewmodel.SettingViewModel
import com.example.ahha_android.util.setStatusBarColor

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by viewModels()
    lateinit var navController: NavController
    private var notificationOn: Boolean = true
    private var notificationString: String = "YES"

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

        getNotificationOption()
        initClickListener()
    }


    private fun getNotificationOption(){
        binding.switchInfoNotification.setOnClickListener {
            notificationOn = !notificationOn

            if(notificationOn){
                notificationString = "YES"
                Log.d("***************checked", notificationString)
            }else{
                notificationString = "NO"
                Log.d("***************checked", notificationString)
            }
        }
    }

    private fun initClickListener(){
        binding.linearLayoutMailNotificationMenu.setOnClickListener{
            Log.d("*********notificationOn",notificationString)

            val bundle = Bundle()
            bundle.putString("notificationString", notificationString)

            navController.navigate(
                R.id.actionSettingFragmentToSettingNotificationFragment,
                bundle
            )
        }
    }
}