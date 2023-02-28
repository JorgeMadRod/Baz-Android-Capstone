package com.jmadrigal.capstone.features.books.repository

import com.jmadrigal.capstone.core.models.AvailableBook
import javax.inject.Inject

class FakeBooksRepository @Inject constructor() : BooksRepository {

    private val books = mutableListOf<AvailableBook>(
        AvailableBook("BTC_MXN", "", "", "", "", "", ""),
        AvailableBook("DOGE_USD", "", "", "", "", "", ""),
        AvailableBook("LT_BTC", "", "", "", "", "", "")
    )

    override suspend fun search(query: String): List<AvailableBook> {
            return books.filter { it.book == query }
    }

    override suspend fun getBooks(): List<AvailableBook> {
        return books
    }

    override suspend fun getRxBooks(): List<AvailableBook> {
        return books
    }

    override suspend fun getLocalBooks(): List<AvailableBook> {
        return books
    }
}