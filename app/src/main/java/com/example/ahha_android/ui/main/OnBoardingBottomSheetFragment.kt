package com.example.ahha_android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentOnBoardingBottomSheetBinding
import com.example.ahha_android.ui.main.adapter.OnBoardingAdapter
import com.example.ahha_android.ui.viewmodel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentOnBoardingBottomSheetBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var onBoardingAdapter: OnBoardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.RoundedCornerBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBottomSheetBinding.inflate(inflater, container, false)

        initViewPager()
        initTabLayoutIndicator()

        return binding.root
    }

    private fun initViewPager() {
        onBoardingAdapter = OnBoardingAdapter()
        binding.viewPager.run {
            adapter = onBoardingAdapter
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }

    private fun initTabLayoutIndicator() {
        TabLayoutMediator(binding.tabLayoutIndicator, binding.viewPager) { tab, _ ->
            tab.view.isClickable = false
        }.attach()
    }
}