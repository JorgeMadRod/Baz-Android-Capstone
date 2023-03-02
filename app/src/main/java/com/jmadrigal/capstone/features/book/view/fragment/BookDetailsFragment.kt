package com.jmadrigal.capstone.features.book.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.core.models.Book
import com.jmadrigal.capstone.databinding.FragmentBookDetailsBinding
import com.jmadrigal.capstone.features.book.view.adapter.AsksAdapter
import com.jmadrigal.capstone.features.book.viewmodel.BookDetailViewModel
import com.jmadrigal.capstone.utils.convertToCurrency

class BookDetailsFragment : Fragment(), TabLayout.OnTabSelectedListener {

    private var _binding: FragmentBookDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookDetailViewModel by activityViewModels()
    private lateinit var book: AvailableBook

    private val askAdapter: AsksAdapter by lazy { AsksAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        _binding?.let {
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        binding.shimmer.startShimmer()
        binding.tabLayout.addOnTabSelectedListener(this)

        val safeArgs: BookDetailsFragmentArgs by navArgs()
        book = safeArgs.book

        viewModel.getOrderBook(book.book)
        viewModel.getTicker(book.book)
    }

    private fun setupObservers() {
        viewModel.ticker.observe(viewLifecycleOwner) { response ->
            response?.let {
                loadValues(it)
            }
        }

        viewModel.orderBook.observe(viewLifecycleOwner) { response ->
            response?.let {
                setupRecycler()
            }
        }
    }

    private fun loadValues(book: Book) {
        if (book.book.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.info_no_available), Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        } else {
            binding.txtTitle.text = book.book.uppercase().split("_")[0]
            binding.lblPrice.text = getString(R.string.price_details, book.book.uppercase().split("_")[1])
            binding.txtPrice.text = book.last.convertToCurrency()
            binding.txtHighPrice.text = book.high.convertToCurrency()
            binding.txtLowPrice.text = book.low.convertToCurrency()
        }

    }

    private fun setupRecycler() {
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        onTabSelected(binding.tabLayout.getTabAt(0))
        binding.shimmer.stopShimmer()
        binding.shimmer.visibility = View.GONE
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab?.position) {
            0 -> {
                binding.rvList.adapter = askAdapter
                val list = viewModel.orderBook.value?.asks
                askAdapter.submitList(list)

            }
            1 -> {
                binding.rvList.adapter = askAdapter
                askAdapter.submitList(viewModel.orderBook.value?.bids)
            }
        }

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onResume() {
        super.onResume()
        _binding?.shimmer?.startShimmer()
    }

    override fun onPause() {
        _binding?.shimmer?.stopShimmer()
        super.onPause()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}