package com.demobnb.propertylisting.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

/**
 * Represents the detailed information of a property.
 *
 * This data class is used as a Room entity to store property details in the local database.
 *
 * @property id The unique identifier for the property. This is the primary key.
 * @property title The title or name of the property.
 * @property guestCount The maximum number of guests the property can accommodate.
 * @property bedroomCount The number of bedrooms in the property.
 * @property bedCount The total number of beds in the property.
 * @property bathCount The number of bathrooms in the property.
 * @property address The full address of the property.
 * @property averageRate The average rating of the property, typically on a scale (e.g., 1-5).
 * @property reviewStandout A standout or featured review for the property.
 * @property reviewCount The total number of reviews the property has received.
 * @property user The ID of the user (host) who owns or listed the property.
 * @property featureImages A list of URLs or paths to the feature images of the property.
 * @property highlights A list of integer identifiers representing the key features or amenities of the property.
 * @property introduction A descriptive introduction or summary of the property.
 * @property longitude The geographical longitude coordinate of the property.
 * @property latitude The geographical latitude coordinate of the property.
 * @property neighborHighlights Information about the neighborhood or nearby attractions.
 * @property availabilityStart The starting date of the property's availability.
 * @property availabilityEnd The ending date of the property's availability.
 * @property cancellationPolicy The ID or type of the cancellation policy applicable to the property.
 */
@Entity(tableName = "property_detail")
data class PropertyDetail(
    @PrimaryKey
    val id: Long,
    val title: String,
    val guestCount: Int,
    val bedroomCount: Int,
    val bedCount: Int,
    val bathCount: Int,
    val address: String,
    val averageRate: Float,
    val reviewStandout: String,
    val reviewCount: Int,
    val user: Long,
    val featureImages: List<String>,
    val highlights: List<Int>,
    val introduction: String,
    val longitude: Float,
    val latitude: Float,
    val neighborHighlights: String,
    val availabilityStart: LocalDate,
    val availabilityEnd: LocalDate,
    val cancellationPolicy: Long,

    )