package com.example.ahha_android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentPlantExchangeBinding

class PlantExchangeFragment : Fragment() {
    private lateinit var binding: FragmentPlantExchangeBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentPlantExchangeBinding.inflate(inflater, container, false)

        setImage()
        countBroccoliNum()
        countGreenOnionNum()
        countTomatoNum()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    private fun setImage() {
        binding.imageViewBroccoli.clipToOutline = true
        binding.imageViewGreenOnion.clipToOutline = true
        binding.imageViewTomato.clipToOutline = true
    }

    private fun countBroccoliNum(){
        val broccoliNum = binding.textViewBroccoliNum
        var num = 0;

        binding.textViewBroccoliMinus.setOnClickListener{
            if(num > 0){
                num--
            }
            broccoliNum.text = num.toString()
        }

        binding.textViewBroccoliPlus.setOnClickListener{
            num++
            broccoliNum.text = num.toString()
        }
    }

    private fun countGreenOnionNum(){
        val greenOnionNum = binding.textViewGreenOnionNum
        var num = 0;

        binding.textViewGreenOnionMinus.setOnClickListener{
            if(num > 0){
                num--
            }
            greenOnionNum.text = num.toString()
        }

        binding.textViewGreenOnionPlus.setOnClickListener{
            num++
            greenOnionNum.text = num.toString()
        }
    }

    private fun countTomatoNum(){
        val tomatoNum = binding.textViewTomatoNum
        var num = 0;

        binding.textViewTomatoMinus.setOnClickListener{
            if(num > 0){
                num--
            }
            tomatoNum.text = num.toString()
        }

        binding.textViewTomatoPlus.setOnClickListener{
            num++
            tomatoNum.text = num.toString()
        }
    }
}