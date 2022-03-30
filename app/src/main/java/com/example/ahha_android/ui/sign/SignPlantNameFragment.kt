package com.example.ahha_android.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.ahha_android.R
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.databinding.FragmentSignPlantNameBinding
import com.example.ahha_android.ui.main.MainActivity
import com.example.ahha_android.ui.viewmodel.ResetViewModel
import com.example.ahha_android.ui.viewmodel.SignViewModel
import com.example.ahha_android.util.BindingAdapter.setDrawableImage
import com.example.ahha_android.util.setStatusBarColor

class SignPlantNameFragment : Fragment() {
    private lateinit var binding: FragmentSignPlantNameBinding
    private val signViewModel: SignViewModel by activityViewModels()
    private val resetViewModel: ResetViewModel by viewModels()
    lateinit var kind: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignPlantNameBinding.inflate(inflater, container, false)
        binding.viewModel = signViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setStatusBarColor(requireActivity(), R.color.white)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCharacterImage()
        checkInputBlank()
    }

    private fun setCharacterImage() {
        val characterNum = arguments?.getInt("characterNum")

        when (characterNum) {
            1 -> {
                binding.imageViewCharacter.setDrawableImage(R.drawable.ic_green_onion_level_5)
                Log.d("ViewPagerFragment", "Page $characterNum")
            }
            2 -> {
                binding.imageViewCharacter.setDrawableImage(R.drawable.ic_tomato_level_5)
                Log.d("ViewPagerFragment", "Page $characterNum")
            }
            3 -> {
                binding.imageViewCharacter.setDrawableImage(R.drawable.ic_broccoli_level_5)
                Log.d("ViewPagerFragment", "Page $characterNum")
            }
        }
    }

    private fun checkInputBlank() {
        binding.editTextCharacterName.addTextChangedListener {
            if (!binding.editTextCharacterName.text.isNullOrBlank()) {
                binding.buttonFinish.isActivated = true
                finishClickListener()
            } else {
                binding.buttonFinish.isActivated = false
            }
        }
    }

    private fun finishClickListener() {
        binding.buttonFinish.setOnClickListener {
            val characterNum = arguments?.getInt("characterNum")
            if (characterNum != null) {
                when (characterNum) {
                    1 -> kind = "GREENONION"
                    2 -> kind = "TOMATO"
                    3 -> kind = "BROCCOLI"
                }
            }
            onFinish()
        }
    }


    private fun onFinish(){
        makePlant()
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }


    private fun makePlant() {
        val name = binding.editTextCharacterName.text
        if (EasyPeasySharedPreference.getAccessToken().isNullOrBlank()) {
            signViewModel.createPlant(name, kind)
        } else {
            resetViewModel.resetPlant(name, kind)
        }
    }
}