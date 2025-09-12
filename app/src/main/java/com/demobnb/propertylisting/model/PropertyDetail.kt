package com.demobnb.propertylisting.model

import java.time.LocalDate

data class PropertyDetail(
    val id: Long,
    val title: String,
    val guestCount: Int,
    val bedroomCount: Int,
    val bedCount: Int,
    val bathCount: Int,
    val address: String,
    val averageRate: Float,
    val reviewCount: Int,
    val host: Host,
    val featureImages: List<String>,
    val highlights: List<Int>,
    val introduction: String,
    val amenities: List<Facility>,
    val longitude: Float,
    val latitude: Float,
    val neighborHighlights: String,
    val availabilityStart: LocalDate,
    val availabilityEnd: LocalDate,
    val cancellationPolicy: Long,
    val reviews: List<Review>

) {
    val featureDescription1 = "$bedroomCount bedroom, $bedCount bed, $bathCount bath"
}