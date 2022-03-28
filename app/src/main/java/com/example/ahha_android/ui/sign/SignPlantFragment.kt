package com.example.ahha_android.ui.sign

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentSignPlantBinding
import com.example.ahha_android.ui.sign.adapter.SignPlantAdapter
import com.example.ahha_android.ui.viewmodel.SignViewModel
import com.example.ahha_android.util.BindingAdapter.setDrawableImage
import java.lang.Math.abs

class SignPlantFragment : Fragment() {
    private lateinit var binding: FragmentSignPlantBinding
    private val viewModel: SignViewModel by activityViewModels()
    lateinit var navController: NavController
    private var scrollPosition: Int = 0
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignPlantBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        viewModel.setCharacterList()

        addListener()
        addBackPressedCallback()
        setCharacterAdapter()
        setCharacterObserve()
    }

    private fun addListener() {
        binding.imageViewBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun addBackPressedCallback() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setCharacterAdapter() {
        val characterListAdapter = SignPlantAdapter()
        binding.viewPager.adapter = characterListAdapter
        // 관리하는 페이지 수. default = 1
        binding.viewPager.offscreenPageLimit = 4
        binding.viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        // item_view 간의 양 옆 여백을 상쇄할 값
        val offsetBetweenPages =
            resources.getDimensionPixelOffset(R.dimen.offsetBetweenPages).toFloat()
        binding.viewPager.setPageTransformer { page, position ->
            val myOffset = position * -(5 * offsetBetweenPages)
            if (position < -1) {
                page.translationX = -myOffset
            } else if (position <= 1) {
                // Paging 시 Y축 Animation 배경색을 약간 연하게 처리
                val scaleFactor = 0.8f.coerceAtLeast(1 - abs(position))
                page.translationX = myOffset
                page.scaleY = scaleFactor
                page.alpha = scaleFactor
            } else {
                page.alpha = 0f
                page.translationX = myOffset
            }
        }
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // Paging 완료되면 호출
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                scrollPosition = position
                Log.d("ViewPagerFragment", "Page ${scrollPosition + 1}")

                when (scrollPosition + 1) {
                    1 -> {
                        binding.imageViewCharacter.setDrawableImage(R.drawable.ic_green_onion_level_5)
                    }
                    2 -> {
                        binding.imageViewCharacter.setDrawableImage(R.drawable.ic_tomato_level_5)
                    }
                    3 -> {
                        binding.imageViewCharacter.setDrawableImage(R.drawable.ic_broccoli_level_5)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                if (state == 1) {
                    binding.buttonFinish.isActivated = true
                    binding.buttonFinish.setOnClickListener {
                        val bundle = bundleOf("characterNum" to scrollPosition + 1)
                        navController.navigate(
                            R.id.actionSignPlantFragmentToSignPlantNameFragment,
                            bundle
                        )
                    }
                }
            }
        })
    }

    private fun setCharacterObserve() {
        viewModel.characterList.observe(viewLifecycleOwner) { characterList ->
            with(binding.viewPager.adapter as SignPlantAdapter) {
                setCharacter(characterList)
            }
        }
    }
}