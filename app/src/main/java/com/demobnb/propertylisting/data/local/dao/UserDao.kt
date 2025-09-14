package com.demobnb.propertylisting.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demobnb.propertylisting.model.User

/**
 * Data Access Object (DAO) for the User table.
 *
 * This interface defines the database interactions for User entities.
 * It includes methods for inserting, retrieving, and deleting users.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(user: User)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>


    @Query("SELECT * FROM user WHERE id = :userId")
    suspend fun getUserById(userId: Long): User?

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}