package com.jmadrigal.capstone.features.books.repository

import com.jmadrigal.capstone.core.database.AvailableBookDao
import com.jmadrigal.capstone.core.database.dto.AvailableBookModel
import com.jmadrigal.capstone.core.models.AvailableBook
import com.jmadrigal.capstone.core.network.BitsoService
import io.reactivex.schedulers.Schedulers

open class BooksRepositoryImpl(private val bitsoService: BitsoService,
                               private val dao: AvailableBookDao) : BooksRepository {

   override suspend fun search(query: String): List<AvailableBook> {
        val result = dao.searchBook("%$query%")
        return result.map { it.toAvailableBook() }
    }

    override suspend fun getRxBooks(): List<AvailableBook> {
        return try {
            val response = bitsoService.getRxBooks()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .map { it.payload }.blockingSingle()

            val books = response.map { AvailableBookModel.fromAvailableBook(it) }
            dao.saveAvailableBooks(books)
            getLocalBooks()
        } catch (ex: Exception) {
            ex.printStackTrace()
            getLocalBooks()
        }
    }

    override suspend fun getBooks(): List<AvailableBook> {
        return try {
            val response = bitsoService.getBooks().payload
            val books = response.map { AvailableBookModel.fromAvailableBook(it) }
            dao.saveAvailableBooks(books)
            getLocalBooks()
        } catch (ex: Exception) {
            ex.printStackTrace()
            getLocalBooks()
        }
    }

    override suspend fun getLocalBooks(): List<AvailableBook> {
        val localBooks = dao.getAvailableBook()
        return localBooks.map { it.toAvailableBook() }
    }

}