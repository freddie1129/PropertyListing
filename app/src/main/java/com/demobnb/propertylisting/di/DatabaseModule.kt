package com.demobnb.propertylisting.di

import android.content.Context
import androidx.room.Room
import com.demobnb.propertylisting.data.local.AppDatabase
import com.demobnb.propertylisting.data.local.dao.PropertyDetailDao
import com.demobnb.propertylisting.data.local.dao.PropertySummaryDao
import com.demobnb.propertylisting.data.local.dao.ReviewDao
import com.demobnb.propertylisting.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module that provides database-related dependencies.
 *
 * This module is installed in the [SingletonComponent], meaning that the provided dependencies
 * will have a singleton scope and will be available throughout the application's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    @Provides
    fun provideSummaryDao(db: AppDatabase): PropertySummaryDao = db.propertySummaryDao()

    @Provides
    fun providePropertyDetailDao(db: AppDatabase): PropertyDetailDao = db.propertyDetailDao()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideReviewDao(db: AppDatabase): ReviewDao = db.reviewDao()
}