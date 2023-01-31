package com.jmadrigal.capstone.features.books.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jmadrigal.capstone.databinding.FragmentAvailableBooksBinding
import com.jmadrigal.capstone.features.books.viewmodel.BooksViewModel

class AvailableBooksFragment : Fragment() {

    private var binding: FragmentAvailableBooksBinding? = null
    private val booksViewModel : BooksViewModel by lazy { BooksViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAvailableBooksBinding.inflate(inflater, container, false)
        binding?.let {
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        booksViewModel.getBooks()
    }

    private fun setupObservers(){
        booksViewModel.books.observe(viewLifecycleOwner){
            println("---> ${it.payload.size}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}