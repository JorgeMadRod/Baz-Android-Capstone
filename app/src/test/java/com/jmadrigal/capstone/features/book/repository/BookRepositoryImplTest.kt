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
import org.mockito.kotlin.any

class BookRepositoryImplTest {

    private val listOfOrderBooks = listOf(
        OrderBookModel("A", "1", "1", listOf(), listOf()),
        OrderBookModel("B", "1", "1", listOf(), listOf()),
        OrderBookModel("C", "1", "1", listOf(), listOf())
    )

    @RelaxedMockK
    lateinit var service: BitsoService

    @RelaxedMockK
    lateinit var dao: BookDao
    lateinit var repository: BookRepository

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
    fun `when we execute getOrderBook with the book id then it returns the book detail and a list of bids and asks`() =
        runBlocking {
            // Given

            val bookId = "A"
            coEvery { service.getOrderBook(bookId) } returns OrderBookResponse(true, OrderBook("", "", listOf(), listOf()))
            coEvery { dao.saveOrderBook(any()) } returns any()
            coEvery { dao.getOrderBook(bookId) } returns listOfOrderBooks.find { it.book == bookId }!!
            // When
            val result = repository.getOrderBook(bookId)
            // Then
            Assert.assertNotNull(result)
        }

}