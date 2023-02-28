package com.jmadrigal.capstone.di

import com.jmadrigal.capstone.core.database.BookDao
import com.jmadrigal.capstone.core.network.BitsoService
import com.jmadrigal.capstone.features.book.repository.BookRepository
import com.jmadrigal.capstone.features.book.repository.BookRepositoryImpl
import com.jmadrigal.capstone.features.books.repository.BooksRepository
import com.jmadrigal.capstone.features.books.repository.FakeBooksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    /* @Provides
     @Named("testDatabase")
     fun providesInMemoryRoom(@ApplicationContext context: Context) =
         Room.inMemoryDatabaseBuilder(context, CapstoneDatabase::class.java)
             .allowMainThreadQueries()
             .build()*/


    @Singleton
    @Provides
    fun providesFakeRepository(): BooksRepository =
        FakeBooksRepository() as BooksRepository

    @Singleton
    @Provides
    fun providesBookRepository(bitsoService: BitsoService, dao: BookDao): BookRepository =
        BookRepositoryImpl(bitsoService, dao)
}