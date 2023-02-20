package com.jmadrigal.capstone.features.book.repository

import com.jmadrigal.capstone.core.models.Book
import com.jmadrigal.capstone.core.models.OrderBook

interface BookRepository {

    suspend fun getTicker(book: String): Book
    suspend fun getLocalBook(book: String): Book
    suspend fun getOrderBook(book: String): OrderBook
    suspend fun getLocalOrderBook(book: String): OrderBook
}