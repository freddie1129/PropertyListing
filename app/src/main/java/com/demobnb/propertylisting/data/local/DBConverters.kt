package com.demobnb.propertylisting.data.local

import androidx.room.TypeConverter
import java.time.LocalDate

class DBConverters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString() // e.g. "2025-09-13"
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it) }
    }
}