package com.example.pruebaintermediakotlin.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaintermediakotlin.databinding.ConsumptionItemBinding

class ConAdapter: RecyclerView.Adapter<ConAdapter.ConsumptionVH>() {
    private var mListConsumptions = listOf<Consumptions>()
    private val selectedConsumptions = MutableLiveData<Consumptions>()
    fun selectedItem(): LiveData<Consumptions> = selectedConsumptions
    fun update(listConsumptions: List<Consumptions>){
        mListConsumptions = listConsumptions
        notifyDataSetChanged()

    }
    inner class ConsumptionVH(private val binding: ConsumptionItemBinding):RecyclerView.ViewHolder(binding.root),View.OnClickListener{
        fun bind(consumptions: Consumptions){
            binding.textView1.text = consumptions.item
            binding.textView2.text = consumptions.price.toString()
            binding.textView3.text = consumptions.quantity.toString()
            binding.textView4.text = consumptions.total.toString()
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedConsumptions.value = mListConsumptions[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsumptionVH {
        return ConsumptionVH(ConsumptionItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: ConsumptionVH, position: Int) {
        val consumptions = mListConsumptions[position]
        holder.bind(consumptions)
    }
    override fun getItemCount(): Int= mListConsumptions.size


}