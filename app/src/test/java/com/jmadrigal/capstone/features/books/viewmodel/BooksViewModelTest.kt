package com.jmadrigal.capstone.features.books.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jmadrigal.capstone.core.database.AvailableBookDao
import com.jmadrigal.capstone.core.database.dto.AvailableBookModel
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.core.models.AvailableBooksResponse
import com.jmadrigal.capstone.core.models.Book
import com.jmadrigal.capstone.core.network.BitsoService
import com.jmadrigal.capstone.features.books.repository.BooksRepositoryImpl
import com.jmadrigal.capstone.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val listOfBooks = listOf<AvailableBook>(
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
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
        repository = BooksRepositoryImpl(service, dao)
        viewModel = BooksViewModel(repository)
    }

    @Test
    fun getRxBooks() = runBlocking {
        //Given
        val response = AvailableBooksResponse(true, listOfBooks)
        coEvery { service.getBooks() } returns response
        coEvery { dao.getAvailableBook() } returns listOfBooks.map { AvailableBookModel.fromAvailableBook(it) }
        //When
        viewModel.getBooks()
        val result = viewModel.books.getOrAwaitValue().find {
            it.book == "A" && it.minimumAmount == "1" && it.maximumAmount == "1" && it.minimumPrice == "1" && it.maximumPrice == "2" && it.minimumValue == "2" && it.maximumValue == "2"
        }
        Assert.assertEquals(result, listOfBooks[0])

    }

     @Test
     fun search() {
         //Given
         val response = Book("A", "1", "1", "1", "", "2", "2", "2", "")
         //When

         //Then
     }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}