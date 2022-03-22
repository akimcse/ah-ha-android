package com.example.ahha_android.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSignPlantNameBinding
import com.example.ahha_android.ui.main.MainActivity
import com.example.ahha_android.ui.viewmodel.SignViewModel
import com.example.ahha_android.util.BindingAdapter.setDrawableImage
import kotlinx.coroutines.NonCancellable.start

class SignPlantNameFragment : Fragment() {
    private lateinit var binding: FragmentSignPlantNameBinding
    private val viewModel: SignViewModel by viewModels()
    lateinit var navController: NavController
    lateinit var kind: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignPlantNameBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setCharacterImage()
        checkInputBlank()

        return binding.root
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
                initClickListener()
            }
        }
    }

    private fun initClickListener(){
        binding.buttonFinish.setOnClickListener {
            val characterNum = arguments?.getInt("characterNum")
            val name = binding.editTextCharacterName.text
            if (characterNum != null) {
                when (characterNum) {
                    1 -> {
                        kind = "GREENONION"
                    }
                    2 -> {
                        kind = "TOMATO"
                    }
                    3 -> {
                        kind = "BROCCOLI"
                    }
                }
            }

            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

            viewModel.createPlant(name, kind)
        }
    }
}