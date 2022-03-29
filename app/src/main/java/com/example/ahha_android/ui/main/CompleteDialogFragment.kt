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
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
            buttonClickListener.onExchangeClicked()
            dismiss()
        }

        binding.buttonFinish.setOnClickListener {
            Log.d("************clicked","새로 키우기")
            buttonClickListener.onFinishClicked()
            dismiss()
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

    interface OnButtonClickListener {
        fun onExchangeClicked()
        fun onFinishClicked()
    }

    private lateinit var buttonClickListener: OnButtonClickListener

    fun setButtonClickListener(buttonClickListener: OnButtonClickListener) {
        this.buttonClickListener = buttonClickListener
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
}