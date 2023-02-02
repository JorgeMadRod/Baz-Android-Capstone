package com.jmadrigal.capstone.features.books.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jmadrigal.capstone.databinding.RowAvailableBooksBinding
import com.jmadrigal.capstone.models.Book

class AvailableBooksAdapter(private val bookSelected: (Book) -> Unit) : ListAdapter<Book, AvailableBooksAdapter.ViewHolder>(DiffUtilsCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowAvailableBooksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                val bookValues = this.book.uppercase().split("_")
                binding.txtName.text = "${bookValues[0]} \u25b8 ${bookValues[1]}"
                Glide.with(binding.root.context)
                    .load("https://cryptoicons.org/api/icon/${bookValues[0]}/200")
                    .error("https://coinicons-api.vercel.app/api/icon/${this.book.split("_")[0]}")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(binding.imgCoin)

                binding.item.setOnClickListener { bookSelected(this) }
            }
        }
    }

    inner class ViewHolder(val binding: RowAvailableBooksBinding) : RecyclerView.ViewHolder(binding.root)

    private class DiffUtilsCallBack : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
            oldItem.bid == newItem.bid
    }
}