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
                    1 -> R.drawable.ic_green_onion_level_1
                    2 -> R.drawable.ic_green_onion_level_2
                    3 -> R.drawable.ic_green_onion_level_3
                    4 -> R.drawable.ic_green_onion_level_4
                    else -> R.drawable.ic_green_onion_level_5
                }
            }
            TOMATO -> {
                when (level) {
                    1 -> R.drawable.ic_tomato_level_1
                    2 -> R.drawable.ic_tomato_level_2
                    3 -> R.drawable.ic_tomato_level_3
                    4 -> R.drawable.ic_tomato_level_4
                    else -> R.drawable.ic_tomato_level_5
                }
            }
            BROCCOLI -> {
                when (level) {
                    1 -> R.drawable.ic_broccoli_level_1
                    2 -> R.drawable.ic_broccoli_level_2
                    3 -> R.drawable.ic_broccoli_level_3
                    4 -> R.drawable.ic_broccoli_level_4
                    else -> R.drawable.ic_broccoli_level_5
                }
            }
        }
    }

    fun getPlantImage(): Int {
        return when (this) {
            GREENONION -> R.drawable.ic_green_onion_level_5
            TOMATO -> R.drawable.ic_tomato_level_5
            BROCCOLI -> R.drawable.ic_broccoli_level_5
        }
    }
}