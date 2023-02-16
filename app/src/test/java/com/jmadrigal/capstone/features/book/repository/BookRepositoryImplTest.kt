package com.jmadrigal.capstone.features.book.repository

import com.jmadrigal.capstone.core.database.BookDao
import com.jmadrigal.capstone.core.database.dto.BookModel
import com.jmadrigal.capstone.core.database.dto.OrderBookModel
import com.jmadrigal.capstone.core.models.Book
import com.jmadrigal.capstone.core.models.OrderBook
import com.jmadrigal.capstone.core.models.OrderBookResponse
import com.jmadrigal.capstone.core.models.TickerResponse
import com.jmadrigal.capstone.core.network.BitsoService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BookRepositoryImplTest {

    @RelaxedMockK
    lateinit var service: BitsoService

    @RelaxedMockK
    lateinit var dao: BookDao
    lateinit var repository: BookRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = BookRepositoryImpl(service, dao)
    }

    @Test
    fun testGetTicker() = runBlocking {
        // Given
        val response = TickerResponse(true, Book("A", "", "", "", "", "", "", "", ""))
        coEvery { service.getTicker("A") } returns response
        coEvery { dao.getBook("A") } returns BookModel.fromBook(response.payload)
        // When
        val result = repository.getTicker("A")
        // Then
        Assert.assertEquals(result, response.payload)
    }

    @Test
    fun testGetOrderBook() = runBlocking {
        // Given
        val response = OrderBookResponse(true, OrderBook("", "", listOf(), listOf()))
        coEvery { service.getOrderBook("A") } returns response
        coEvery { dao.getOrderBook("A") } returns OrderBookModel.fromOrderBook("A", response.payload)
        // When
        val result = repository.getOrderBook("A")
        // Then
        Assert.assertEquals(result, response.payload)
    }
}