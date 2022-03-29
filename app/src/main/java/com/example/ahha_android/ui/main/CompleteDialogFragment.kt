package com.example.ahha_android.ui.main

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentCompleteDialogBinding
import com.example.ahha_android.ui.viewmodel.MainViewModel
import com.example.ahha_android.util.BindingAdapter.setDrawableImage
import com.example.ahha_android.util.setStatusBarColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CompleteDialogFragment : DialogFragment() {
    private var _binding: FragmentCompleteDialogBinding? = null
    private val viewModel: MainViewModel by viewModels()
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompleteDialogBinding.inflate(inflater, container, false)
        setStatusBarColor(requireActivity(), R.color.blue)

        setObserver()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.apply {
                fetchPlant()
            }
        }

        binding.buttonExchange.setOnClickListener {
            Log.d("************clicked","식물교환")
            dismiss()
            onButtonExchange()
        }

        binding.buttonFinish.setOnClickListener {
            Log.d("************clicked","닫기")
            dismiss()
            onButtonFinish()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawableResource(R.drawable.img_complete_dialog)
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObserver(){
        viewModel.plantKind.observe(viewLifecycleOwner) {
            viewModel.plantLevel.value?.let { level ->
                binding.imageViewPlant.setDrawableImage(it.getPlantImageByLevel(level))
            }
        }

        viewModel.plantKind.observe(viewLifecycleOwner) {
            viewModel.plantName.value?.let { name ->
                binding.textViewTitleName.text = name
            }
        }
    }

    private fun onButtonExchange(){
        // 식물 교환 뷰로 navigation
    }

    private fun onButtonFinish(){
        // 로그인 마친 상태 -> 내 캐릭터 생성 페이지로 넘어갈 때 signActivity 어떻게 되는지
    }
}