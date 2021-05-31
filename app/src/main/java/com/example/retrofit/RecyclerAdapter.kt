package com.example.retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemLayoutBinding

class RecyclerAdapter(private val items: MutableList<ItemModel>)
    : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.MyViewHolder {
        val itemView = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.MyViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class MyViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: ItemModel
        fun bind() {
            model = items[adapterPosition]
            binding.tvName.text = model.name
            binding.tvBorders.text = model.borders.toString()
            binding.tvCapital.text = model.capital
            binding.tvLanguages.text = model.languages.toString()
        }
    }
}