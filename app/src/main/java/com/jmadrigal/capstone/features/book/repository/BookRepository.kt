package com.jmadrigal.capstone.features.book.repository

import android.annotation.SuppressLint
import com.jmadrigal.capstone.core.database.BookDao
import com.jmadrigal.capstone.core.database.dto.BookModel
import com.jmadrigal.capstone.core.database.dto.OrderBookModel
import com.jmadrigal.capstone.core.models.Book
import com.jmadrigal.capstone.core.models.OrderBook
import com.jmadrigal.capstone.core.network.BitsoService

class BookRepository(private val bitsoService: BitsoService,
                     private val dao: BookDao) {

    suspend fun getTicker(book: String): Book {
        return try {
            val response = bitsoService.getTicker(book).payload
            val bookResponse = BookModel.fromBook(response)
            dao.saveBook(bookResponse)
            getLocalBook(book)
        } catch (ex: Exception) {
            ex.printStackTrace()
            getLocalBook(book)
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private suspend fun getLocalBook(book: String): Book {
        val localBook = dao.getBook(book)
        if (localBook == null){
            return Book("", "", "", "", "", "", "", "", "")
        } else
        return localBook.toBook()
    }

    suspend fun getOrderBook(id: String): OrderBook {
        return try {
            val response = bitsoService.getOrderBook(id).payload
            val orderBookModel = OrderBookModel.fromOrderBook(id, response)
            dao.saveOrderBook(orderBookModel)
            getLocalOrderBook(id)
        } catch (ex: Exception) {
            ex.printStackTrace()
            getLocalOrderBook(id)
        }
    }

    private suspend fun getLocalOrderBook(id: String): OrderBook {
        val localOrderBook = dao.getOrderBook(id)
        if (localOrderBook == null) {
            return OrderBook("", "", listOf(), listOf())
        } else
            return localOrderBook.toOrderBook()
    }

}