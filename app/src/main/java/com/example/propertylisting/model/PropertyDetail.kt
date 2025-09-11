package com.example.propertylisting.model

import java.time.LocalDate

data class PropertyDetail(
    val id: Long,
    val title: String,
    val address: String,
    val averageRate: Float,
    val reviewCount: Int,
    val owner: Owner,
    val featureImages: List<String>,
    val introduction: String,
    val amenities: List<Facility>,
    val longitude: Float,
    val latitude: Float,
    val neighborHighlights: String,
    val availabilityStart: LocalDate,
    val availabilityEnd: LocalDate,
    val cancellationPolicy: Long,
    val reviews: List<Review>

)