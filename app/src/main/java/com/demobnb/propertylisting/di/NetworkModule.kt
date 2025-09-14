package com.demobnb.propertylisting.di

import com.demobnb.propertylisting.BuildConfig
import com.demobnb.propertylisting.data.remote.RemoteDateAdapter
import com.demobnb.propertylisting.data.remote.RemotePropertyService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import javax.inject.Singleton


/**
 * Dagger Hilt module for providing network-related dependencies.
 *
 * This module provides instances of OkHttpClient, Gson, Retrofit, and the RemotePropertyService.
 * It is installed in the SingletonComponent, meaning that the provided dependencies will be
 * singleton instances throughout the application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    private const val BASE_URL = BuildConfig.HOSTNAME

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, RemoteDateAdapter())
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RemotePropertyService {
        return retrofit.create(RemotePropertyService::class.java)
    }


}