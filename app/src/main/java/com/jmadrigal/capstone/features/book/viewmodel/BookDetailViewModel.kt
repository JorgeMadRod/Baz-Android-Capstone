package com.jmadrigal.capstone.features.book.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmadrigal.capstone.core.models.Book
import com.jmadrigal.capstone.core.models.OrderBook
import com.jmadrigal.capstone.features.book.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val repository: BookRepository) : ViewModel() {

    private val _orderBook = MutableStateFlow<OrderBook?>(null)
    val orderBook: StateFlow<OrderBook?> = _orderBook
    private val _ticker = MutableStateFlow<Book?>(null)
    val ticker: StateFlow<Book?> = _ticker

    fun getOrderBook(id: String) {
        viewModelScope.launch {
            val result = repository.getOrderBook(id)
            _orderBook.value = result
        }
    }

    fun getTicker(book: String) {
        viewModelScope.launch {
            val result = repository.getTicker(book)
            _ticker.value = result
        }
    }
}