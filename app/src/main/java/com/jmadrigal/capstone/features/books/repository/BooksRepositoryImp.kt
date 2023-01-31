package com.jmadrigal.capstone.features.books.repository

import com.jmadrigal.capstone.models.AvailableBooksResponse
import com.jmadrigal.capstone.models.OrderBook
import com.jmadrigal.capstone.network.NetworkManagerImp

class BooksRepositoryImp : BooksRepository {

    private val networkManager by lazy { NetworkManagerImp() }

    override suspend fun getBooks(): AvailableBooksResponse =
        networkManager.get<AvailableBooksResponse>("https://api.bitso.com/v3/available_books/", mapOf())


    override suspend fun getOrderBook(id: String): List<OrderBook> =
        networkManager.get<List<OrderBook>>("https://api.bitso.com/v3/order_book/", mapOf())
}