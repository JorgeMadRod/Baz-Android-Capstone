package com.jmadrigal.capstone.features.books.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmadrigal.capstone.models.AvailableBooksResponse
import com.jmadrigal.capstone.network.BitsoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val repository: BitsoRepository) : ViewModel() {

    private val _books = MutableLiveData<AvailableBooksResponse>()
    val books: LiveData<AvailableBooksResponse> = _books

    fun getBooks() {
        viewModelScope.launch {
            val result = repository.getBooks()
            _books.postValue(result)
        }
    }
}