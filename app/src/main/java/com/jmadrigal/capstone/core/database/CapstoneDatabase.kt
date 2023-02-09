package com.jmadrigal.capstone.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jmadrigal.capstone.core.database.dto.AvailableBookModel
import com.jmadrigal.capstone.core.database.dto.BookModel
import com.jmadrigal.capstone.core.database.dto.OrderBookModel

@Database(entities = [BookModel::class, AvailableBookModel::class, OrderBookModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class CapstoneDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun availableBookDao(): AvailableBookDao
}