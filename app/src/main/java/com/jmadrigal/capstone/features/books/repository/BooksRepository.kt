package com.jmadrigal.capstone.features.books.repository

import com.jmadrigal.capstone.core.network.BitsoService

class BooksRepository(private val bitsoService: BitsoService) {
    suspend fun getBooks() =
        bitsoService.getBooks().payload

}