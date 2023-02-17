package com.jmadrigal.capstone.di

import com.jmadrigal.capstone.core.database.AvailableBookDao
import com.jmadrigal.capstone.core.database.BookDao
import com.jmadrigal.capstone.core.network.BitsoService
import com.jmadrigal.capstone.features.book.repository.BookRepositoryImpl
import com.jmadrigal.capstone.features.books.repository.BooksRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesBooksRepository(bitsoService: BitsoService, dao: AvailableBookDao) =
        BooksRepositoryImpl(bitsoService, dao)

    @Singleton
    @Provides
    fun providesBookRepository(bitsoService: BitsoService, dao: BookDao) =
        BookRepositoryImpl(bitsoService, dao)
}