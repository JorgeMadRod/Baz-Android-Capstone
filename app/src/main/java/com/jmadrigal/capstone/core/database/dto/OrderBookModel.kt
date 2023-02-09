package com.jmadrigal.capstone.core.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmadrigal.capstone.core.database.Converters
import com.jmadrigal.capstone.core.models.OrderBook

@Entity(tableName = "orderBook")
data class OrderBookModel(
    @PrimaryKey
    var book: String,
    var updated_at: String,
    var sequence: String,
    var bids: String,
    var asks: String
) {
    companion object {
        fun fromOrderBook(book: String, orderBook: OrderBook): OrderBookModel {
            return OrderBookModel(
                book = book,
                updated_at = orderBook.updated_at,
                sequence = orderBook.sequence,
                bids = Converters().asksFromArrayList(orderBook.bids),
                asks = Converters().asksFromArrayList(orderBook.asks)
            )
        }
    }

    fun toOrderBook(): OrderBook {
        return OrderBook(
            updated_at = this.updated_at,
            sequence = this.sequence,
            bids = Converters().asksFromString(this.bids),
            asks =  Converters().asksFromString(this.asks)
        )
    }
}
