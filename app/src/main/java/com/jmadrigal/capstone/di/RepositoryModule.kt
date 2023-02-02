package com.jmadrigal.capstone.di

import com.jmadrigal.capstone.network.BitsoRepository
import com.jmadrigal.capstone.network.BitsoService
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
    fun providesBitsoRepository(bitsoService: BitsoService) =
        BitsoRepository(bitsoService)
}