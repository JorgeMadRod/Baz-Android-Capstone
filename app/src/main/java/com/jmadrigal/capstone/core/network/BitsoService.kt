package com.jmadrigal.capstone.core.network

import com.jmadrigal.capstone.core.models.AvailableBooksResponse
import com.jmadrigal.capstone.core.models.OrderBookResponse
import com.jmadrigal.capstone.core.models.TickerResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoService {

    @GET("available_books")
    suspend fun getBooks(): AvailableBooksResponse

    @GET("ticker")
    suspend fun getTicker(@Query("book") book: String): TickerResponse

    @GET("order_book")
    suspend fun getOrderBook(@Query("book") book: String): OrderBookResponse
}