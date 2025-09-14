package com.demobnb.propertylisting.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.temporal.ChronoUnit


/**
 * Represents a summary of a property listing.
 *
 * This data class is used to store and display essential information about a property,
 * such as its ID, user ID, title, feature image, check-in/out dates, extended price, and rate.
 *
 * @property id The unique identifier for the property summary.
 * @property userId The ID of the user associated with this property summary.
 * @property title The title of the property.
 * @property featureImage The URL or path to the feature image of the property.
 * @property checkInDate The check-in date for the property.
 * @property checkOutDate The check-out date for the property.
 * @property extendedPrice The total price for the duration of the stay.
 * @property rate The nightly rate for the property.
 * @property duration The duration of the stay in days, calculated from checkInDate and checkOutDate.
 */
@Entity(tableName = "property_summary")
data class PropertySummary(
    @PrimaryKey
    val id: Long,
    val userId: Long,
    val title: String,
    val featureImage: String,
    val checkInDate: LocalDate,
    val checkOutDate: LocalDate,
    val extendedPrice: Float,
    val rate: Float
) {

    val duration: Long
        get() = ChronoUnit.DAYS.between(checkInDate, checkOutDate)
}