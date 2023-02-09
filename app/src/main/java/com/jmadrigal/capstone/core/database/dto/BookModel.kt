package com.jmadrigal.capstone.core.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmadrigal.capstone.core.models.Book

@Entity(tableName = "book")
data class BookModel(
    @PrimaryKey
    val book: String,
    val volume: String,
    val high: String,
    val last: String,
    val low: String,
    val vwap: String,
    val ask: String,
    val bid: String,
    val createdAt: String,
) {

    companion object {
        fun fromBook(book: Book): BookModel {
            return BookModel(
                book = book.book,
                volume = book.volume,
                high = book.high,
                last = book.last,
                low = book.low,
                vwap = book.vwap,
                ask = book.ask,
                bid = book.bid,
                createdAt = book.createdAt
            )
        }
    }

    fun toBook(): Book {
        return Book(
            book = this.book,
            volume = this.volume,
            high = this.high,
            last = this.last,
            low = this.low,
            vwap = this.vwap,
            ask = this.ask,
            bid = this.bid,
            createdAt = this.createdAt
        )
    }

}
