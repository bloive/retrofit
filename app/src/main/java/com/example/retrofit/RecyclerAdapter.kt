package com.example.retrofit

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemLayoutBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

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
            binding.tvCapital.text = model.capital
            binding.tvLanguages.text = model.languages.toString()
            loadImg(model.flag)
        }

        private fun loadImg(url: String?) {
            GlideToVectorYou
                .init()
                .with(binding.root.context)
                .load(Uri.parse(url), binding.Image)
        }
    }

    fun setData(items:MutableList<ItemModel>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }
}