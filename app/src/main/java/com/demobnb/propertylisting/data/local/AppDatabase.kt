package com.demobnb.propertylisting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demobnb.propertylisting.data.local.dao.PropertyDetailDao
import com.demobnb.propertylisting.data.local.dao.PropertySummaryDao
import com.demobnb.propertylisting.data.local.dao.ReviewDao
import com.demobnb.propertylisting.data.local.dao.UserDao
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User


/**
 * Represents the Room database for the application.
 *
 * This class defines the database configuration, including the entities it manages
 * and the DAOs (Data Access Objects) used to interact with the data.
 *
 * @property propertySummaryDao DAO for accessing PropertySummary data.
 * @property userDao DAO for accessing User data.
 * @property propertyDetailDao DAO for accessing PropertyDetail data.
 * @property reviewDao DAO for accessing Review data.
 */
@Database(
    entities = [PropertySummary::class,
        PropertyDetail::class,
        User::class,
        Review::class], version = 2
)
@TypeConverters(DBConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun propertySummaryDao(): PropertySummaryDao
    abstract fun userDao(): UserDao
    abstract fun propertyDetailDao(): PropertyDetailDao
    abstract fun reviewDao(): ReviewDao
}

