package com.jmadrigal.capstone.features.books.repository

import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.core.models.AvailableBooksResponse
import com.jmadrigal.capstone.core.network.BitsoService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class BooksRepositoryTest {

    @RelaxedMockK
    lateinit var service: BitsoService
    lateinit var repository: BooksRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = BooksRepository(service)
    }

    @Test
    fun testSuccessBooks() = runBlocking {
        val response = AvailableBooksResponse(true, ArrayList<AvailableBook>())
        // Given
        coEvery { service.getBooks() } returns response
        // When
        val books = repository.getBooks()
        // Then
        Assert.assertEquals(response.payload, books)
    }
}