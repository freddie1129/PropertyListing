package com.demobnb.propertylisting.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demobnb.propertylisting.model.PropertySummary

/**
 * Data Access Object (DAO) for accessing and managing [com.demobnb.propertylisting.model.PropertySummary] data in the local database.
 *
 * This interface provides methods to insert, retrieve, and delete property summaries.
 * It uses Room persistence library annotations to define database operations.
 */
@Dao
interface PropertySummaryDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(propertySummary: PropertySummary)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(properties: List<PropertySummary>)

    @Query("SELECT * FROM property_summary")
    suspend fun getAll(): List<PropertySummary>


    @Query("DELETE FROM property_summary")
    suspend fun deleteAll()
}