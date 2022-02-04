package com.example.ahha_android.data.type

import com.example.ahha_android.R

enum class Plant {
    GREENONION,
    TOMATO,
    BROCCOLI;

    fun getPlantImageByLevel(level: Int): Int {
        return when (this) {
            GREENONION -> {
                when (level) {
                    1 -> R.drawable.ic_launcher_foreground
                    2 -> R.drawable.ic_launcher_foreground
                    3 -> R.drawable.ic_launcher_foreground
                    4 -> R.drawable.ic_launcher_foreground
                    else -> R.drawable.ic_launcher_foreground
                }
            }
            TOMATO -> {
                when (level) {
                    1 -> R.drawable.ic_launcher_foreground
                    2 -> R.drawable.ic_launcher_foreground
                    3 -> R.drawable.ic_launcher_foreground
                    4 -> R.drawable.ic_launcher_foreground
                    else -> R.drawable.ic_launcher_foreground
                }
            }
            BROCCOLI -> {
                when (level) {
                    1 -> R.drawable.ic_launcher_foreground
                    2 -> R.drawable.ic_launcher_foreground
                    3 -> R.drawable.ic_launcher_foreground
                    4 -> R.drawable.ic_launcher_foreground
                    else -> R.drawable.ic_launcher_foreground
                }
            }
        }
    }

    fun getPlantFrontImage(): Int {
        return when (this) {
            GREENONION -> R.drawable.ic_launcher_foreground
            TOMATO -> R.drawable.ic_launcher_foreground
            BROCCOLI -> R.drawable.ic_launcher_foreground
        }
    }

    fun getPlantBackImage(): Int {
        return when (this) {
            GREENONION -> R.drawable.ic_launcher_background
            TOMATO -> R.drawable.ic_launcher_background
            BROCCOLI -> R.drawable.ic_launcher_background
        }
    }
}