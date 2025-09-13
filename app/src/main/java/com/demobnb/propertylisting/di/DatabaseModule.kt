package com.demobnb.propertylisting.di

import android.content.Context
import androidx.room.Room
import com.demobnb.propertylisting.data.local.AppDatabase
import com.demobnb.propertylisting.data.local.PropertySummaryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_db"
        ).build()
    }

    @Provides
    fun provideUserDao(db: AppDatabase): PropertySummaryDao = db.propertySummaryDao()
}