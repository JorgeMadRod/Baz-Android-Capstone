package com.jmadrigal.capstone.features.books.view.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.databinding.FragmentAvailableBooksBinding
import com.jmadrigal.capstone.features.book.view.fragment.BookDetailsFragment
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
        viewModel.getBooks()
    }

    private fun setupListeners() {
        binding.searchChip.setOnCloseIconClickListener {
            handleChip(null)
            availableBooksAdapter.filter.filter("")
        }
    }

    private fun setupObservers() {
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
        val fragment = BookDetailsFragment.newInstance(book)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commitAllowingStateLoss()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.mSearch)
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.queryHint = "Buscar una cripto"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                availableBooksAdapter.filter.filter(query)
                handleChip(query)
                searchItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun handleChip(query: String?) {
        binding.searchChip.visibility = if (query == null) View.GONE else View.VISIBLE
        binding.searchChip.text = query
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