package com.jmadrigal.capstone.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jmadrigal.capstone.core.database.dto.AvailableBookModel

@Dao
interface AvailableBookDao {

    @Query("SELECT * FROM availableBook")
    suspend fun getAvailableBook(): List<AvailableBookModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAvailableBooks(availableBooks: List<AvailableBookModel>)
}