package com.jmadrigal.capstone.features.books.view.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.databinding.FragmentAvailableBooksBinding
import com.jmadrigal.capstone.features.books.view.adapter.AvailableBooksAdapter
import com.jmadrigal.capstone.features.books.viewmodel.BooksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvailableBooksFragment : Fragment() {

    private var _binding: FragmentAvailableBooksBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BooksViewModel by activityViewModels()
    private val availableBooksAdapter by lazy { AvailableBooksAdapter { onBookSelected(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAvailableBooksBinding.inflate(inflater, container, false)
        _binding?.let {
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupObservers()
        binding.shimmer.startShimmer()
        viewModel.getRxBooks()
    }

    private fun setupListeners() {
        binding.searchChip.setOnCloseIconClickListener {
            viewModel.search(null)
        }
    }

    private fun setupObservers() {
        viewModel.chip.observe(viewLifecycleOwner){
            binding.searchChip.visibility = if (it == null) View.GONE else View.VISIBLE
            binding.searchChip.text = it
        }

        viewModel.books.observe(viewLifecycleOwner) {
            setupRecycler(it)
        }
    }

    private fun setupRecycler(bookList: List<AvailableBook>) {
        availableBooksAdapter.submitList(bookList)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = availableBooksAdapter
        }
        binding.shimmer.stopShimmer()
        binding.shimmer.visibility = View.GONE
    }

    private fun onBookSelected(book: AvailableBook) {
        navToDetails(book)
    }

    private fun navToDetails(book: AvailableBook) {
        val action = AvailableBooksFragmentDirections.actionNavToDetail(book)
        findNavController().navigate(action)
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