package com.jmadrigal.capstone.network

import com.jmadrigal.capstone.models.AvailableBooksResponse
import com.jmadrigal.capstone.models.TickerResponse
import retrofit2.http.GET

interface BitsoService {

    @GET("available_books")
    suspend fun getBooks(): AvailableBooksResponse

    @GET("ticker")
    suspend fun getTicker(): TickerResponse

    @GET("order_book")
    suspend fun getOrderBook()
}