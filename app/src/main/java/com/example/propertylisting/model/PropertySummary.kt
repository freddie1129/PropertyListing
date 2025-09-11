package com.example.propertylisting.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date

data class PropertySummary(
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