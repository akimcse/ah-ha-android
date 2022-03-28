package com.example.ahha_android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentPlantExchangeBinding

class PlantExchangeFragment : Fragment() {
    private lateinit var binding: FragmentPlantExchangeBinding
    private var sum = 0;

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

    private fun setImage() {
        binding.imageViewBroccoli.clipToOutline = true
        binding.imageViewGreenOnion.clipToOutline = true
        binding.imageViewTomato.clipToOutline = true
    }

    private fun countBroccoliNum(){
        var num = 0;

        binding.textViewBroccoliMinus.setOnClickListener{
            if(num > 0){
                num--
                sum--
            }
            binding.textViewBroccoliNum.text = num.toString()
            binding.textViewPossibleSum.text = sum.toString()
        }

        binding.textViewBroccoliPlus.setOnClickListener{
            if(sum < 4){
                num++
                sum++
            }
            binding.textViewBroccoliNum.text = num.toString()
            binding.textViewPossibleSum.text = sum.toString()
        }
    }

    private fun countGreenOnionNum(){
        var num = 0;

        binding.textViewGreenOnionMinus.setOnClickListener{
            if(num > 0){
                num--
                sum--
            }
            binding.textViewGreenOnionNum.text = num.toString()
            binding.textViewPossibleSum.text = sum.toString()
        }

        binding.textViewGreenOnionPlus.setOnClickListener{
            if(sum < 4){
                num++
                sum++
            }
            binding.textViewGreenOnionNum.text = num.toString()
            binding.textViewPossibleSum.text = sum.toString()
        }
    }

    private fun countTomatoNum(){
        var num = 0;

        binding.textViewTomatoMinus.setOnClickListener{
            if(num > 0){
                num--
                sum--
            }
            binding.textViewTomatoNum.text = num.toString()
            binding.textViewPossibleSum.text = sum.toString()
        }

        binding.textViewTomatoPlus.setOnClickListener{
            if(sum < 4){
                num++
                sum++
            }
            binding.textViewTomatoNum.text = num.toString()
            binding.textViewPossibleSum.text = sum.toString()
        }
    }
}