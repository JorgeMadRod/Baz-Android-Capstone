package com.jmadrigal.capstone.features.books.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.features.books.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BooksRepository) : ViewModel() {

    private val _books = MutableStateFlow<List<AvailableBook>>(emptyList())
    val books: StateFlow<List<AvailableBook>> = _books
    private val _chip = MutableStateFlow<String?>(null)
    val chip: StateFlow<String?> = _chip

    fun getRxBooks() {
        viewModelScope.launch {
            val result = repository.getRxBooks()
            _books.value = result
        }
    }

    fun search(query: String?) {
        if (query.isNullOrBlank()) {
            getRxBooks()
            _chip.value = null
        } else {
            viewModelScope.launch {
                val result = repository.search(query)
                _chip.value = query
                _books.value = result
            }
        }
    }

    fun getBooks() {
        viewModelScope.launch {
            val result = repository.getBooks()
            _books.value = result
        }
    }

}