package com.demobnb.propertylisting.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.temporal.ChronoUnit


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
        get() =  "$firstName $lastName"
}
