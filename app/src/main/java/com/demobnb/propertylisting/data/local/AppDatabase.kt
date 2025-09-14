package com.demobnb.propertylisting.data.local

import android.widget.RemoteViews
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User


@Database(entities = [PropertySummary::class,
    PropertyDetail::class,
    User::class,
                     Review::class], version = 2)
@TypeConverters(DBConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun propertySummaryDao(): PropertySummaryDao
    abstract fun userDao(): UserDao
    abstract fun propertyDetailDao(): PropertyDetailDao
    abstract fun reviewDao(): ReviewDao
}

@Dao
interface PropertySummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(propertySummary: PropertySummary)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(properties: List<PropertySummary>)

    @Query("SELECT * FROM property_summary")
    suspend fun getAll(): List<PropertySummary>


    @Query("DELETE FROM property_summary")
    suspend fun deleteAll()
}

@Dao
interface PropertyDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(propertyDetail: PropertyDetail)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(propertyDetails: List<PropertyDetail>)

    @Query("SELECT * FROM property_detail")
    suspend fun getAll(): List<PropertyDetail>

    @Query("SELECT * FROM property_detail WHERE id = :propertyId") // Corrected query
    suspend fun getPropertyDetailById(propertyId: Long): PropertyDetail? // Return type is nullable

    @Query("DELETE FROM property_detail")
    suspend fun deleteAll()
}

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>


    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUserById(userId: Long): User? // Return type is n

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(reviews: List<Review>)

    @Query("SELECT * FROM review")
    suspend fun getAll(): List<Review>

    //@Query("SELECT * FROM review WHERE propertyId = :propertyId ORDER BY createAt DESC")
    @Query("SELECT * FROM review WHERE propertyId = :propertyId")
    suspend fun getReviewsByPropertyId(propertyId: Long): List<Review>

    @Query("DELETE FROM review WHERE propertyId = :propertyId")
    suspend fun deleteReviewsByPropertyId(propertyId: Long): Int // Returns count

    @Query("DELETE FROM review")
    suspend fun deleteAll()
}
