package com.jmadrigal.capstone.di

import android.content.Context
import androidx.room.Room
import com.jmadrigal.capstone.core.database.CapstoneDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("testDatabase")
    fun providesInMemoryRoom(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, CapstoneDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}