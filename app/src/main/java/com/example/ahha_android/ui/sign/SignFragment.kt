package com.example.ahha_android.ui.sign

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSignBinding
import com.example.ahha_android.ui.viewmodel.SignViewModel

class SignFragment : Fragment() {
    private lateinit var binding: FragmentSignBinding
    private val viewModel: SignViewModel by viewModels()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.buttonSignUp.setOnClickListener{
            navController.navigate(R.id.actionSignFragmentToSignPlantFragment)
        }
    }
}