package com.jmadrigal.capstone.features.books.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmadrigal.capstone.features.books.repository.BooksRepository
import com.jmadrigal.capstone.features.books.repository.BooksRepositoryImp
import com.jmadrigal.capstone.models.Book
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    private val repository : BooksRepository by lazy { BooksRepositoryImp() }
    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>> = _books

    fun getBooks(){
        viewModelScope.launch {
            _books.postValue(repository.getBooks())
        }
    }
}