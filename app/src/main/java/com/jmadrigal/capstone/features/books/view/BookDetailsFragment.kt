package com.jmadrigal.capstone.features.books.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jmadrigal.capstone.databinding.FragmentBookDetailsBinding

class BookDetailsFragment : Fragment() {

    private var _binding: FragmentBookDetailsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookDetailsBinding.inflate(inflater, container, false)
        _binding?.let {
            return it.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}