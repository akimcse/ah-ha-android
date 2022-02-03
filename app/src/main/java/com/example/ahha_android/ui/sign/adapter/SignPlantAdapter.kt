package com.example.ahha_android.ui.sign.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ahha_android.data.vo.SignPlantData
import com.example.ahha_android.databinding.ItemPlantBinding

class SignPlantAdapter : RecyclerView.Adapter<SignPlantAdapter.PlantViewHolder>() {

    private var characterList = emptyList<SignPlantData>()

    inner class PlantViewHolder(
        private val binding : ItemPlantBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(characterData: SignPlantData){
            binding.viewModel = characterData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding = ItemPlantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int = characterList.size

    fun setCharacter(characterList: List<SignPlantData>){
        this.characterList = characterList
        notifyDataSetChanged()
    }
}