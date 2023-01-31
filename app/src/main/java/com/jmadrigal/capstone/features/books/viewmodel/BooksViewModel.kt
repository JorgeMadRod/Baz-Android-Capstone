package com.jmadrigal.capstone.features.books.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmadrigal.capstone.features.books.repository.BooksRepository
import com.jmadrigal.capstone.features.books.repository.BooksRepositoryImp
import com.jmadrigal.capstone.models.AvailableBooksResponse
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    private val repository: BooksRepository by lazy { BooksRepositoryImp() }
    private val _books = MutableLiveData<AvailableBooksResponse>()
    val books: LiveData<AvailableBooksResponse> = _books

    fun getBooks() {
        viewModelScope.launch {
            val result = repository.getBooks()
            println("---> ${result.toString()}")
            //_books.postValue()
        }
    }
}