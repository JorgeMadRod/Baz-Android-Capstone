package com.jmadrigal.capstone.features.books.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmadrigal.capstone.R
import com.jmadrigal.capstone.databinding.FragmentAvailableBooksBinding
import com.jmadrigal.capstone.features.books.viewmodel.BooksViewModel
import com.jmadrigal.capstone.models.Book
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvailableBooksFragment : Fragment() {

    private var _binding: FragmentAvailableBooksBinding? = null
    private val binding get() = _binding
    private val viewModel: BooksViewModel by activityViewModels()
    private val availableBooksAdapter: AvailableBooksAdapter by lazy { AvailableBooksAdapter { onBookSelected(it) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAvailableBooksBinding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel.getBooks()
    }

    private fun setupObservers() {
        viewModel.books.observe(viewLifecycleOwner) {
            setupRecycler()
        }
    }

    private fun setupRecycler() {
        availableBooksAdapter.submitList(viewModel.books.value?.payload)
        binding!!.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = availableBooksAdapter
        }
    }

    private fun onBookSelected(book: Book) {
        val fragment = BookDetailsFragment().apply {

        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commitAllowingStateLoss()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}