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
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.databinding.RowAvailableBooksBinding
import com.jmadrigal.capstone.utils.OnItemSelected

class AvailableBooksAdapter(private var bookSelected: OnItemSelected<AvailableBook>) :
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
                binding.txtName.text = holder.binding.root.context.getString(R.string.available_books_name, bookValues[0], bookValues[1])
                Glide.with(binding.root.context)
                    .load(holder.binding.root.context.getString(R.string.url_icon, this.book.split("_")[0]))
                    .error(R.mipmap.ic_launcher_round)
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