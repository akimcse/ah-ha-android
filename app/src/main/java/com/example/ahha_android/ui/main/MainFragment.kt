package com.example.ahha_android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ahha_android.R
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.databinding.FragmentMainBinding
import com.example.ahha_android.ui.viewmodel.MainViewModel
import com.example.ahha_android.util.BindingAdapter.setDrawableImage
import com.example.ahha_android.util.setStatusBarColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        setStatusBarColor(requireActivity(), R.color.blue)

        init()
        addObserver()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.apply {
                fetchUser()
                fetchMailCount()
                fetchPlant()
            }
        }
    }

    private fun init() {
        if (!EasyPeasySharedPreference.getOnBoardingVisit()) {
            binding.textViewTitle.text = getString(R.string.main_title_initial)
            val onBoardingBottomSheet = OnBoardingBottomSheetFragment()
            onBoardingBottomSheet.show(parentFragmentManager, onBoardingBottomSheet.tag)
        } else {
            binding.textViewTitle.text = getString(R.string.main_title)
        }
    }

    private fun addObserver() {
        viewModel.ordinalNumber.observe(viewLifecycleOwner) {
            binding.textViewPlantNumber.text = getString(R.string.main_plant_number_format, it)
        }
        viewModel.mailCount.observe(viewLifecycleOwner) {
            binding.textViewMailCount.text = getString(R.string.main_mail_count_format, it)
        }
        viewModel.plantKind.observe(viewLifecycleOwner) {
            viewModel.plantLevel.value?.let { level ->
                binding.imageViewPlant.setDrawableImage(it.getPlantImageByLevel(level))
            }
        }
    }
}