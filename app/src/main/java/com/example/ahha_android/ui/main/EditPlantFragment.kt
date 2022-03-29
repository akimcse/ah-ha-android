package com.example.ahha_android.ui.main

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ahha_android.R
import com.example.ahha_android.data.type.Plant
import com.example.ahha_android.databinding.FragmentEditPlantBinding
import com.example.ahha_android.databinding.FragmentSignPlantBinding
import com.example.ahha_android.ui.sign.adapter.SignPlantAdapter
import com.example.ahha_android.ui.viewmodel.EditPlantViewModel
import com.example.ahha_android.ui.viewmodel.SignViewModel
import com.example.ahha_android.util.BindingAdapter.setDrawableImage
import com.example.ahha_android.util.setStatusBarColor
import java.lang.Math.abs

class EditPlantFragment : Fragment() {
    private lateinit var binding: FragmentEditPlantBinding
    private val viewModel: EditPlantViewModel by viewModels()
    lateinit var navController: NavController
    lateinit var kind: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditPlantBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setStatusBarColor(requireActivity(), R.color.white)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        viewModel.setCharacterList()
        viewModel.fetchPlant()

        setCharacterObserve()
        setVegeButton()
        setFinishButton()
    }

    private fun setVegeButton() {
        binding.buttonBroccoli.setOnClickListener {
            kind = "BROCCOLI"
            binding.buttonBroccoli.isSelected = true
            binding.buttonGreenOnion.isSelected = false
            binding.buttonTomato.isSelected = false
            setCharacterObserve()
            Log.d("*************clicked","broccoli")
        }

        binding.buttonGreenOnion.setOnClickListener {
            kind = "GREENONION"
            binding.buttonGreenOnion.isSelected = true
            binding.buttonBroccoli.isSelected = false
            binding.buttonTomato.isSelected = false
            setCharacterObserve()
            Log.d("*************clicked","green onion")
        }

        binding.buttonTomato.setOnClickListener {
            kind = "TOMATO"
            binding.buttonTomato.isSelected = true
            binding.buttonGreenOnion.isSelected = false
            binding.buttonBroccoli.isSelected = false
            setCharacterObserve()
            Log.d("*************clicked","tomato")
        }
    }

    private fun setFinishButton(){
        binding.editTextCharacterName.addTextChangedListener {
            if (!binding.editTextCharacterName.text.isNullOrBlank()) {
                binding.buttonFinish.isActivated = true
                finishClickListener()
            } else {
                binding.buttonFinish.isActivated = false
            }
        }
    }

    private fun finishClickListener(){
        binding.buttonFinish.setOnClickListener {
            navController.popBackStack()
            val name = binding.editTextCharacterName.text
            viewModel.changePlantInfo(name, kind)
        }
    }


    private fun setCharacterObserve() {
        viewModel.plantKind.observe(viewLifecycleOwner) {
            viewModel.plantLevel.value?.let { level ->
                binding.imageViewCharacter.setDrawableImage(it.getPlantImageByLevel(level))
            }
        }
    }
}
