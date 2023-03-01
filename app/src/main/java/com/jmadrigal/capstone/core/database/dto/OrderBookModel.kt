package com.jmadrigal.capstone.core.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmadrigal.capstone.core.models.Ask
import com.jmadrigal.capstone.core.models.OrderBook

@Entity(tableName = "orderBook")
data class OrderBookModel(
    @PrimaryKey
    var book: String,
    var updatedAt: String,
    var sequence: String,
    var bids: List<Ask>?,
    var asks: List<Ask>?
) {
    companion object {
        fun fromOrderBook(book: String, orderBook: OrderBook): OrderBookModel {
            return OrderBookModel(
                book = book,
                updatedAt = orderBook.updatedAt,
                sequence = orderBook.sequence,
                bids = orderBook.bids,
                asks = orderBook.asks
            )
        }
    }

    fun toOrderBook(): OrderBook {
        return OrderBook(
            updatedAt = this.updatedAt,
            sequence = this.sequence,
            bids = this.bids ?: listOf(),
            asks = this.asks ?: listOf()
        )
    }
}
