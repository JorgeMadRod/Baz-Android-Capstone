package com.jmadrigal.capstone.features.books.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.features.books.repository.BooksRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BooksRepositoryImpl) : ViewModel() {

    private val _books = MutableLiveData<List<AvailableBook>>()
    val books: LiveData<List<AvailableBook>> = _books
    private val _chip = MutableLiveData<String?>()
    val chip: LiveData<String?> = _chip

    fun getRxBooks() {
        viewModelScope.launch {
            val result = repository.getRxBooks()
            _books.postValue(result)
        }
    }

    fun search(query: String?) {
        if (query.isNullOrBlank()) {
            getRxBooks()
            _chip.postValue(null)
        } else {
            viewModelScope.launch {
                val result = repository.search(query)
                _chip.postValue(query)
                _books.postValue(result)
            }
        }
    }

    fun getBooks() {
        viewModelScope.launch {
            val result = repository.getBooks()
            _books.postValue(result)
        }
    }
}