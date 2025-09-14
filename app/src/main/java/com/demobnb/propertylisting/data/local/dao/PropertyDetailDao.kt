package com.demobnb.propertylisting.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demobnb.propertylisting.model.PropertyDetail

/**
 * Data Access Object (DAO) for the [com.demobnb.propertylisting.model.PropertyDetail] entity.
 * Provides methods to interact with the property_detail table in the local database.
 */
@Dao
interface PropertyDetailDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(propertyDetail: PropertyDetail)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(propertyDetails: List<PropertyDetail>)

    @Query("SELECT * FROM property_detail")
    suspend fun getAll(): List<PropertyDetail>

    @Query("SELECT * FROM property_detail WHERE id = :propertyId") // Corrected query
    suspend fun getPropertyDetailById(propertyId: Long): PropertyDetail? // Return type is nullable

    @Query("DELETE FROM property_detail")
    suspend fun deleteAll()
}