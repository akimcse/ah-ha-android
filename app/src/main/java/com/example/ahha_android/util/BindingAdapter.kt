package com.example.ahha_android.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter("src")
    @JvmStatic
    fun ImageView.setDrawableImage(@DrawableRes imageResource: Int) {
        setImageResource(imageResource)
    }
}