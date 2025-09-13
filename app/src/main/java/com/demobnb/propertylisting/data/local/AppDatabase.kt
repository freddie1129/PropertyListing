package com.demobnb.propertylisting.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demobnb.propertylisting.model.PropertySummary


@Database(entities = [PropertySummary::class], version = 1)
@TypeConverters(DBConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun propertySummaryDao(): PropertySummaryDao
}

@Dao
interface PropertySummaryDao {

    @Insert
    suspend fun insert(propertySummary: PropertySummary)

    @Insert
    suspend fun insertAll(properties: List<PropertySummary>)

    @Query("SELECT * FROM property_summary")
    suspend fun getAll(): List<PropertySummary>


    @Query("DELETE FROM property_summary")
    suspend fun deleteAll()
}