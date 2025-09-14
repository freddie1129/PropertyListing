package com.demobnb.propertylisting.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demobnb.propertylisting.model.Review

/**
 * Data Access Object for the review table.
 */
@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(review: Review)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(reviews: List<Review>)

    @Query("SELECT * FROM review")
    suspend fun getAll(): List<Review>

    @Query("SELECT * FROM review WHERE propertyId = :propertyId")
    suspend fun getReviewsByPropertyId(propertyId: Long): List<Review>

    @Query("DELETE FROM review WHERE propertyId = :propertyId")
    suspend fun deleteReviewsByPropertyId(propertyId: Long): Int // Returns count

    @Query("DELETE FROM review")
    suspend fun deleteAll()
}