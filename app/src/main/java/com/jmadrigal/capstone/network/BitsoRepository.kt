package com.jmadrigal.capstone.network

class BitsoRepository(private val bitsoService: BitsoService) {
    suspend fun getBooks() = bitsoService.getBooks()
    suspend fun getTicker() = bitsoService.getTicker()
    suspend fun getOrderBook() = bitsoService.getOrderBook()
}