package com.jmadrigal.capstone.di

import com.jmadrigal.capstone.core.network.BitsoService
import com.jmadrigal.capstone.features.book.repository.BookRepository
import com.jmadrigal.capstone.features.books.repository.BooksRepository
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
    fun providesBooksRepository(bitsoService: BitsoService) =
        BooksRepository(bitsoService)

    @Singleton
    @Provides
    fun providesBookRepository(bitsoService: BitsoService) =
        BookRepository(bitsoService)
}