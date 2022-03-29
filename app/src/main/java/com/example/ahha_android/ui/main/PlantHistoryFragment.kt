package com.example.ahha_android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentPlantHistoryBinding
import com.example.ahha_android.ui.main.adapter.PlantHistoryAdapter
import com.example.ahha_android.ui.viewmodel.PlantHistoryViewModel
import com.example.ahha_android.util.setStatusBarColor

class PlantHistoryFragment : Fragment() {
    private lateinit var binding: FragmentPlantHistoryBinding
    private val viewModel: PlantHistoryViewModel by viewModels()
    private lateinit var plantHistoryAdapter: PlantHistoryAdapter
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlantHistoryBinding.inflate(inflater, container, false)
        setStatusBarColor(requireActivity(), R.color.white)

        initRecyclerView()
        addObserver()
        initClickListener()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
    }

    private fun initRecyclerView() {
        plantHistoryAdapter = PlantHistoryAdapter()
        binding.recyclerViewPlantHistory.adapter = plantHistoryAdapter
        binding.recyclerViewPlantHistory.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun addObserver() {
        viewModel.plantHistoryData.observe(viewLifecycleOwner) {
            plantHistoryAdapter.data = it
            plantHistoryAdapter.notifyDataSetChanged()
        }
    }

    private fun initClickListener(){
        binding.buttonGoToExchange.setOnClickListener{
            navController.navigate(R.id.actionplantHistoryFragmentToPlantExchangeFragment)
        }
    }
}