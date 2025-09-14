package com.demobnb.propertylisting.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.temporal.ChronoUnit


/**
 * Represents a user entity in the application.
 *
 * This data class is used to store user information, including personal details,
 * hosting information, and reviews. It is annotated with `@Entity` for Room persistence.
 *
 * @property id The unique identifier for the user. This is the primary key.
 * @property firstName The first name of the user.
 * @property lastName The last name of the user.
 * @property avatar A URL or path to the user's avatar image.
 * @property feature A descriptive feature or characteristic of the user (e.g., "Superhost").
 * @property address The address of the user.
 * @property reviewCount The total number of reviews the user has received.
 * @property averageRate The average rating the user has received from reviews.
 * @property createdAt The date when the user account was created.
 * @property firstHostAt The date when the user first started hosting.
 * @property introduction A short introduction or bio provided by the user.
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Long,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val feature: String,
    val address: String,
    val reviewCount: Int,
    val averageRate: Float,
    val createdAt: LocalDate,
    val firstHostAt: LocalDate,
    val introduction: String
) {

    val hostDurationMonths: Long
        get() = ChronoUnit.MONTHS.between(firstHostAt, LocalDate.now())


    val name: String
        get() = "$firstName $lastName"
}
