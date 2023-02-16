package com.jmadrigal.capstone.features.books.viewmodel

import com.jmadrigal.capstone.core.database.AvailableBookDao
import com.jmadrigal.capstone.core.database.dto.AvailableBookModel
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.core.models.AvailableBooksResponse
import com.jmadrigal.capstone.core.network.BitsoService
import com.jmadrigal.capstone.features.books.repository.BooksRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BooksViewModelTest {

    val listOfBooks = listOf<AvailableBook>(
        AvailableBook("A", "1", "1", "1", "2", "2", "2"),
        AvailableBook("B", "1", "1", "1", "2", "2", "2"),
        AvailableBook("C", "1", "1", "1", "2", "2", "2"))

    @RelaxedMockK
    lateinit var service: BitsoService

    @RelaxedMockK
    lateinit var dao: AvailableBookDao
    lateinit var repository: BooksRepositoryImpl
    lateinit var viewModel: BooksViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = BooksRepositoryImpl(service, dao)
        viewModel = BooksViewModel(repository)
    }

    @Test
    fun getRxBooks() = runBlocking {
        val response = AvailableBooksResponse(true, listOfBooks)
        //G
        coEvery { service.getBooks() } returns response
        coEvery { dao.getAvailableBook() } returns listOfBooks.map { AvailableBookModel.fromAvailableBook(it) }
        //W
        viewModel.getBooks()
        //T
        Assert.assertEquals(viewModel.books.value, listOfBooks)
    }

    @Test
    fun search() {
        //W
        //G
        //T
    }

}