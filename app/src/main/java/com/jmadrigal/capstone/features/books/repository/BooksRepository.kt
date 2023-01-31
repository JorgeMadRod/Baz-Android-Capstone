package com.jmadrigal.capstone.features.books.repository

import com.jmadrigal.capstone.models.AvailableBooksResponse
import com.jmadrigal.capstone.models.Book
import com.jmadrigal.capstone.models.OrderBook


interface BooksRepository {

    suspend fun getBooks(): AvailableBooksResponse
    suspend fun getOrderBook(id:String):List<OrderBook>

}