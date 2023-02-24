package com.jmadrigal.capstone.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.jmadrigal.capstone.core.database.AvailableBookDao
import com.jmadrigal.capstone.core.database.CapstoneDatabase
import com.jmadrigal.capstone.core.database.dto.AvailableBookModel
import com.jmadrigal.capstone.core.models.AvailableBook
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class CapstonDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: CapstoneDatabase

    private lateinit var dao: AvailableBookDao

    @Before
    fun init() {
        hiltRule.inject()
        dao = database.availableBookDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAvailableBooks() = runTest {

        val listOfAvailableBooks = listOf(
            AvailableBookModel("Book A", "", "", "", "", "", ""),
            AvailableBookModel("Book B", "", "", "", "", "", ""),
            AvailableBookModel("Book C", "", "", "", "", "", "")
        )
        dao.saveAvailableBooks(listOfAvailableBooks)

        val list = dao.getAvailableBook()
        Assert.assertEquals(list, listOfAvailableBooks)

    }

}