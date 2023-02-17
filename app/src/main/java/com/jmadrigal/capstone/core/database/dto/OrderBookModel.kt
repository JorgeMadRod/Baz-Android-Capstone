package com.jmadrigal.capstone.core.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmadrigal.capstone.core.models.Ask
import com.jmadrigal.capstone.core.models.OrderBook

@Entity(tableName = "orderBook")
data class OrderBookModel(
    @PrimaryKey
    var book: String,
    var updated_at: String,
    var sequence: String,
    var bids: List<Ask>?,
    var asks: List<Ask>?
) {
    companion object {
        fun fromOrderBook(book: String, orderBook: OrderBook): OrderBookModel {
            return OrderBookModel(
                book = book,
                updated_at = orderBook.updated_at,
                sequence = orderBook.sequence,
                bids = orderBook.bids,
                asks = orderBook.asks
            )
        }
    }

    fun toOrderBook(): OrderBook {
        return OrderBook(
            updated_at = this.updated_at,
            sequence = this.sequence,
            bids = this.bids ?: listOf(),
            asks = this.asks ?: listOf()
        )
    }
}
