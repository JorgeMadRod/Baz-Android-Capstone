package com.jmadrigal.capstone.features.books.repository

import com.jmadrigal.capstone.core.database.AvailableBookDao
import com.jmadrigal.capstone.core.models.AvailableBooksResponse
import com.jmadrigal.capstone.core.network.BitsoService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class BooksRepositoryImplTest {

    @RelaxedMockK
    lateinit var service: BitsoService

    @RelaxedMockK
    lateinit var dao: AvailableBookDao
    lateinit var repository: BooksRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = BooksRepositoryImpl(service, dao)
    }

    @Test
    fun testSuccessBooks() = runBlocking {
        val response = AvailableBooksResponse(true, ArrayList())
        // Given
        coEvery { service.getBooks() } returns response
        coEvery { dao.getAvailableBook() } returns listOf()
        // When
        val books = repository.getBooks()
        // Then
        Assert.assertEquals(response.payload, books)
    }
}