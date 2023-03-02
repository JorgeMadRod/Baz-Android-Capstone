package com.jmadrigal.capstone.features.book.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmadrigal.capstone.core.models.Book
import com.jmadrigal.capstone.core.models.OrderBook
import com.jmadrigal.capstone.features.book.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val repository: BookRepository) : ViewModel() {

    private val _orderBook = MutableLiveData<OrderBook?>()
    val orderBook: LiveData<OrderBook?> = _orderBook
    private val _ticker = MutableLiveData<Book>()
    val ticker: LiveData<Book> = _ticker

    fun getOrderBook(id: String) {
        viewModelScope.launch {
            val result = repository.getOrderBook(id)
            _orderBook.postValue(result)
        }
    }

    fun getTicker(book: String) {
        viewModelScope.launch {
            val result = repository.getTicker(book)
            _ticker.postValue(result)
        }
    }
}