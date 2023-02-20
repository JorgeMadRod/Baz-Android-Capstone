package com.jmadrigal.capstone.features.books.repository

import com.jmadrigal.capstone.core.models.AvailableBook

interface BooksRepository {

    suspend fun search(query: String): List<AvailableBook>
    suspend fun getBooks(): List<AvailableBook>
    suspend fun getRxBooks(): List<AvailableBook>
    suspend fun getLocalBooks(): List<AvailableBook>
}