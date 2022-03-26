package com.example.ahha_android.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ahha_android.R
import com.example.ahha_android.data.type.Plant
import com.example.ahha_android.data.vo.PlantHistoryData
import com.example.ahha_android.databinding.ItemPlantHistoryBinding
import com.example.ahha_android.util.BindingAdapter.setDrawableImage

class PlantHistoryAdapter : RecyclerView.Adapter<PlantHistoryViewHolder>() {
    var data = listOf<PlantHistoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHistoryViewHolder {
        val binding =
            ItemPlantHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlantHistoryViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: PlantHistoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}

class PlantHistoryViewHolder(
    private val binding: ItemPlantHistoryBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root), Animation.AnimationListener {
    private lateinit var animationToMiddle: Animation
    private lateinit var animationFromMiddle: Animation
    private var isFrontOfCardShowing = true
    private lateinit var plantKind: Plant

    fun bind(data: PlantHistoryData) {
        binding.textViewPlantName.text = data.name
        binding.textViewPlantTime.text =
            context.getString(R.string.plant_history_time_format, data.startTime, data.finishTime)
        binding.imageViewPlant.setDrawableImage(data.kind.getPlantImage())
        plantKind = data.kind

        animationToMiddle = AnimationUtils.loadAnimation(context, R.anim.to_middle)
        animationToMiddle.setAnimationListener(this)

        animationFromMiddle = AnimationUtils.loadAnimation(context, R.anim.from_middle)
        animationFromMiddle.setAnimationListener(this)

        itemView.setOnClickListener {
            binding.apply {
                container.clearAnimation()
                container.animation = animationToMiddle
                container.startAnimation(animationToMiddle)
            }
        }
    }

    override fun onAnimationEnd(animation: Animation) {
        if (animation == animationToMiddle) {
            if (isFrontOfCardShowing) {
                binding.apply {
                    viewOval.setBackgroundResource(R.drawable.oval_plant_history_back)
                    imageViewPlant.isInvisible = true
                    plantInfoContainer.isInvisible = false
                }
            } else {
                binding.apply {
                    viewOval.setBackgroundResource(R.drawable.oval_plant_history_front)
                    plantInfoContainer.isInvisible = true
                    imageViewPlant.setDrawableImage(plantKind.getPlantImage())
                    imageViewPlant.isInvisible = false
                }
            }
            binding.apply {
                container.clearAnimation()
                container.animation = animationFromMiddle
                container.startAnimation(animationFromMiddle)
            }
        } else {
            isFrontOfCardShowing = !isFrontOfCardShowing
        }
    }

    override fun onAnimationStart(animation: Animation?) {}

    override fun onAnimationRepeat(animation: Animation?) {}
}