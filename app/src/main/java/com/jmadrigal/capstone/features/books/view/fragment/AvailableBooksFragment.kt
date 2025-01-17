package com.jmadrigal.capstone.features.books.view.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.databinding.FragmentAvailableBooksBinding
import com.jmadrigal.capstone.features.books.view.adapter.AvailableBooksAdapter
import com.jmadrigal.capstone.features.books.viewmodel.BooksViewModel
import com.jmadrigal.capstone.utils.findNavControllerSafely
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class AvailableBooksFragment @Inject constructor() : Fragment(R.layout.fragment_available_books) {

    private var _binding: FragmentAvailableBooksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BooksViewModel by viewModels()
    private val availableBooksAdapter by lazy { AvailableBooksAdapter { onBookSelected(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAvailableBooksBinding.bind(view)
        setupListeners()
        setupObservers()
        binding.shimmerAvB.startShimmer()
        viewModel.getBooks()
        //viewModel.getRxBooks()
    }

    private fun setupListeners() {
        binding.searchChip.setOnCloseIconClickListener {
            viewModel.search(null)
        }
    }

    private fun setupObservers() {

        lifecycleScope.launch {
            viewModel.books.collect {
                setupRecycler(it)
            }
        }

        lifecycleScope.launch {
            viewModel.chip.collect {
                binding.searchChip.visibility = if (it == null) View.GONE else View.VISIBLE
                binding.searchChip.text = it
            }
        }

    }

    private fun setupRecycler(bookList: List<AvailableBook>) {
        availableBooksAdapter.submitList(bookList)
        binding.rvAvailableBooks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = availableBooksAdapter
        }
        binding.shimmerAvB.stopShimmer()
        binding.shimmerAvB.visibility = View.GONE
    }

    private fun onBookSelected(book: AvailableBook) {
        navToDetails(book)
    }

    private fun navToDetails(book: AvailableBook) {
        val action = AvailableBooksFragmentDirections.actionNavToDetail(book)
        findNavControllerSafely(R.id.availableBooksFragment)?.navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.mSearch)
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = getString(R.string.hint_query_search)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                //availableBooksAdapter.filter.filter(query)
                viewModel.search(query)
                searchItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onResume() {
        super.onResume()
        _binding?.shimmerAvB?.startShimmer()
    }

    override fun onPause() {
        _binding?.shimmerAvB?.stopShimmer()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}