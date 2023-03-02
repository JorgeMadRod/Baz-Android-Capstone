package com.jmadrigal.capstone.features.books.view.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.jmadrigal.capstone.features.book.view.fragment.BookDetailsFragment
import javax.inject.Inject

class BooksFragmentFactory @Inject constructor() : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            AvailableBooksFragment::class.java.name -> AvailableBooksFragment()
            BookDetailsFragment::class.java.name -> BookDetailsFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}