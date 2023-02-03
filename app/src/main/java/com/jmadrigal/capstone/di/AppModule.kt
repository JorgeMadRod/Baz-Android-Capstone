package com.jmadrigal.capstone.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jmadrigal.capstone.core.network.BitsoService
import com.jmadrigal.capstone.core.utils.Constants.BASE_URL
import com.jmadrigal.capstone.core.utils.Constants.DEFAULT_TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson =
        GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.MINUTES)
            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.MINUTES)
            .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
        return client.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun providesBitsoService(retrofit: Retrofit.Builder): BitsoService =
        retrofit
            .build()
            .create(BitsoService::class.java)
}