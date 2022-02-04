package com.example.ahha_android.ui.main

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ahha_android.R
import com.example.ahha_android.databinding.FragmentEditPlantBinding
import com.example.ahha_android.databinding.FragmentSignPlantBinding
import com.example.ahha_android.ui.sign.adapter.SignPlantAdapter
import com.example.ahha_android.ui.viewmodel.EditPlantViewModel
import com.example.ahha_android.ui.viewmodel.SignViewModel
import com.example.ahha_android.util.BindingAdapter.setDrawableImage
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        viewModel.setCharacterList()
        viewModel.fetchPlant()

        setCharacterAdapter()
        setCharacterObserve()
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
                Log.d("ViewPagerFragment", "Page ${position + 1}")

                when (position + 1) {
                    1 -> {
                        binding.imageViewCharacter.setDrawableImage(R.drawable.ic_launcher_foreground)
                    }
                    2 -> {
                        binding.imageViewCharacter.setDrawableImage(R.drawable.ic_launcher_background)
                    }
                    3 -> {
                        binding.imageViewCharacter.setDrawableImage(R.drawable.ic_launcher_foreground)
                    }
                }

                binding.buttonFinish.setOnClickListener {
                    navController.popBackStack()
                    val name = binding.editTextCharacterName.text
                    when (position + 1) {
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
                    viewModel.changePlantInfo(name, kind)
                }

                if (position != 0) {
                    binding.buttonFinish.isActivated = true
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

        viewModel.plantKind.observe(viewLifecycleOwner) {
            viewModel.plantLevel.value?.let { level ->
                binding.imageViewCharacter.setDrawableImage(it.getPlantImageByLevel(level))
            }
        }
    }
}
