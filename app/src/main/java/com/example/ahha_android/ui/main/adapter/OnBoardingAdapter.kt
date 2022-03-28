package com.example.ahha_android.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ahha_android.R
import com.example.ahha_android.databinding.ItemOnBoardingBinding

class OnBoardingAdapter : RecyclerView.Adapter<OnBoardingViewHolder>() {
    private val data = listOf(
        OnBoardingContents(R.string.on_boarding_description_first, R.drawable.ic_on_boarding_first),
        OnBoardingContents(R.string.on_boarding_description_second, R.drawable.ic_on_boarding_second),
        OnBoardingContents(R.string.on_boarding_description_third, R.drawable.ic_on_boarding_third)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding =
            ItemOnBoardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnBoardingViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class OnBoardingViewHolder(
    private val binding: ItemOnBoardingBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: OnBoardingContents) {
        binding.textViewDescription.text = context.getString(data.StringRes)
        Glide.with(context).load(data.imageRes).into(binding.imageViewContent)
    }
}

data class OnBoardingContents(
    val StringRes: Int,
    val imageRes: Int
)