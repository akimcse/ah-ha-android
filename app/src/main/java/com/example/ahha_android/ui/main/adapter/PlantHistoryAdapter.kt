package com.example.ahha_android.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ahha_android.R
import com.example.ahha_android.data.vo.PlantHistoryData
import com.example.ahha_android.databinding.ItemPlantHistoryBinding

class PlantHistoryAdapter() : RecyclerView.Adapter<PlantHistoryViewHolder>() {
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
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: PlantHistoryData) {
        binding.textViewPlantName.text = data.name
        binding.textViewPlantTime.text =
            context.getString(R.string.plant_history_time_format, data.startTime, data.finishTime)
    }
}