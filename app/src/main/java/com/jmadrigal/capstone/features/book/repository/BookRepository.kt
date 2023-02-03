package com.jmadrigal.capstone.features.book.repository

import com.jmadrigal.capstone.core.models.Book
import com.jmadrigal.capstone.core.models.OrderBook
import com.jmadrigal.capstone.core.network.BitsoService

class BookRepository(private val bitsoService: BitsoService) {

    suspend fun getTicker(book: String): Book =
        bitsoService.getTicker(book).payload

    suspend fun getOrderBook(id: String): OrderBook =
        bitsoService.getOrderBook(id).payload
}