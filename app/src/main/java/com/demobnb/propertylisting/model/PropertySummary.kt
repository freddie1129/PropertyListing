package com.demobnb.propertylisting.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Entity(tableName = "property_summary")
data class PropertySummary(
    @PrimaryKey
    val id: Long,
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