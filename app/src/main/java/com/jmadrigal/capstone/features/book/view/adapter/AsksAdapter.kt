package com.jmadrigal.capstone.features.book.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jmadrigal.capstone.databinding.RowTabsBinding
import com.jmadrigal.capstone.core.models.Ask

class AsksAdapter : ListAdapter<Ask, AsksAdapter.ViewHolder>(DiffUtilsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowTabsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                binding.txtAmount.text = this.amount
                binding.txtBook.text = this.book
                binding.txtPrice.text = this.price
            }
        }
    }

    inner class ViewHolder(val binding: RowTabsBinding) : RecyclerView.ViewHolder(binding.root)

    class DiffUtilsCallback : DiffUtil.ItemCallback<Ask>() {
        override fun areItemsTheSame(oldItem: Ask, newItem: Ask): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Ask, newItem: Ask): Boolean =
            oldItem.book == newItem.book

    }
}