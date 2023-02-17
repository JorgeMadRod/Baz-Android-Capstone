package com.jmadrigal.capstone.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmadrigal.capstone.core.database.dto.BookModel
import com.jmadrigal.capstone.core.database.dto.OrderBookModel

@Dao
interface BookDao {

    @Query("SELECT * FROM book  WHERE book LIKE :book")
    suspend fun getBook(book: String): BookModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBook(books: BookModel)

    @Query("SELECT * FROM orderBook WHERE book LIKE :book")
    suspend fun getOrderBook(book: String): OrderBookModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrderBook(orderBookModel: OrderBookModel)
}