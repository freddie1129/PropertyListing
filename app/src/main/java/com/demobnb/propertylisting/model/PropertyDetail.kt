package com.demobnb.propertylisting.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate

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

) {


}