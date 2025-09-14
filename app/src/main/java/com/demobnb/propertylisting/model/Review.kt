package com.demobnb.propertylisting.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * Represents a review for a property.
 *
 * @property id The unique identifier for the review.
 * @property propertyId The ID of the property this review is for.
 * @property userId The ID of the user who wrote this review.
 * @property rating The rating given by the user, typically on a scale (e.g., 1-5).
 * @property comment The textual comment provided by the user.
 * @property createAt The date when the review was created.
 */
@Entity(tableName = "review")
data class Review(
    @PrimaryKey
    val id: Long,
    val propertyId: Long,
    val userId: Long,
    val rating: Int,
    val comment: String,
    val createAt: LocalDate
)
