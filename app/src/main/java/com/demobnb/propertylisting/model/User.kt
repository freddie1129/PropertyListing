package com.demobnb.propertylisting.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit


data class User(val id: Long,
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
    val hostDurationMonths: Long = ChronoUnit.MONTHS.between(firstHostAt, LocalDate.now())

    val name: String = "$firstName $lastName"
}
