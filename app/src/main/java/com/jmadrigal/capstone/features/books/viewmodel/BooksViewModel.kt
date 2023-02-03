package com.jmadrigal.capstone.features.books.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.features.books.repository.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BooksRepository) : ViewModel() {

    private val _books = MutableLiveData<List<AvailableBook>>()
    val books: LiveData<List<AvailableBook>> = _books


    fun getBooks() {
        viewModelScope.launch {
            val result = repository.getBooks()
            _books.postValue(result)
        }
    }

}