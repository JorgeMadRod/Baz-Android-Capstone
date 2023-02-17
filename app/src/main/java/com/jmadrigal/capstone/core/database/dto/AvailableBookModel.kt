package com.jmadrigal.capstone.core.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmadrigal.capstone.core.models.AvailableBook

@Entity(tableName = "availableBook")
data class AvailableBookModel(
    @PrimaryKey
    val book: String,
    val minimumAmount: String,
    val maximumAmount: String,
    val minimumPrice: String,
    val maximumPrice: String,
    val minimumValue: String,
    val maximumValue: String
) {

    companion object{
        fun fromAvailableBook(availableBook: AvailableBook): AvailableBookModel{
            return AvailableBookModel(
                book = availableBook.book,
                minimumAmount = availableBook.minimumAmount,
                maximumAmount = availableBook.maximumAmount,
                minimumPrice = availableBook.minimumPrice,
                maximumPrice = availableBook.maximumPrice,
                minimumValue = availableBook.minimumValue,
                maximumValue = availableBook.maximumValue,
            )
        }
    }

    fun toAvailableBook(): AvailableBook {
        return AvailableBook(
            book = this.book,
            minimumAmount = this.minimumAmount,
            maximumAmount = this.maximumAmount,
            minimumPrice = this.minimumPrice,
            maximumPrice = this.maximumPrice,
            minimumValue = this.minimumValue,
            maximumValue = this.maximumValue,
        )
    }
}