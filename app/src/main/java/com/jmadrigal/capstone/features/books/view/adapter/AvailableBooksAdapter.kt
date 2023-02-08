package com.jmadrigal.capstone.features.books.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.databinding.RowAvailableBooksBinding

class AvailableBooksAdapter(private var bookSelected: (AvailableBook) -> Unit) :
    ListAdapter<AvailableBook, AvailableBooksAdapter.ViewHolder>(DiffUtilsCallBack()), Filterable {

    private var originalList: List<AvailableBook> = currentList.toList()

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

    @Suppress("UNCHECKED_CAST")
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                return FilterResults().apply {
                    values = if (p0.isNullOrEmpty())
                        originalList
                    else
                        onFilter(originalList, p0.toString())
                }
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                submitList(results?.values as? List<AvailableBook>, true)
            }

        }
    }

    override fun submitList(list: List<AvailableBook>?) {
        submitList(list, false)
    }

    private fun submitList(list: List<AvailableBook>?, filtered: Boolean) {
        if (!filtered)
            originalList = list ?: listOf()
        super.submitList(list)
    }
    fun onFilter(list: List<AvailableBook>, constraint: String): List<AvailableBook> {
        val filteredList = list.filter {
            // Estamos filtrando por el nombre del libro
            it.book.contains(constraint.lowercase())
        }
        return filteredList
    }

    inner class ViewHolder(val binding: RowAvailableBooksBinding) : RecyclerView.ViewHolder(binding.root)

    private class DiffUtilsCallBack : DiffUtil.ItemCallback<AvailableBook>() {
        override fun areItemsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: AvailableBook, newItem: AvailableBook): Boolean =
            oldItem.book == newItem.book
    }
}