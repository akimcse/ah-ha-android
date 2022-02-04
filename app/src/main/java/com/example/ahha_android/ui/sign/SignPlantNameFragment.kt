package com.example.ahha_android.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSignPlantNameBinding
import com.example.ahha_android.ui.main.MainActivity
import com.example.ahha_android.ui.viewmodel.SignViewModel
import com.example.ahha_android.util.BindingAdapter.setDrawableImage

class SignPlantNameFragment : Fragment() {
    private lateinit var binding: FragmentSignPlantNameBinding
    private val viewModel: SignViewModel by viewModels()

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

    private fun setCharacterImage(){
        val characterNum = arguments?.getInt("characterNum")

        when(characterNum){
            1 -> {
                binding.imageViewCharacter.setDrawableImage(R.drawable.ic_launcher_foreground)
                Log.d("ViewPagerFragment", "Page $characterNum")
            }
            2 -> {
                binding.imageViewCharacter.setDrawableImage(R.drawable.ic_launcher_background)
                Log.d("ViewPagerFragment", "Page $characterNum")
            }
            3 -> {
                binding.imageViewCharacter.setDrawableImage(R.drawable.ic_launcher_foreground)
                Log.d("ViewPagerFragment", "Page $characterNum")
            }
        }
    }

    // 입력/미입력 구분이 안 먹음 -> 일단 항상 활성화 상태임
    private fun checkInputBlank(){
        binding.buttonFinish.isActivated = true
        binding.buttonFinish.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        //if(binding.editTextCharacterName.text.isNotBlank()){ }
    }
}