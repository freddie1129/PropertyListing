package com.demobnb.propertylisting.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.demobnb.propertylisting.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

fun formatStayRange(checkIn: LocalDate, checkOut: LocalDate): String {
    val dayFormatter = DateTimeFormatter.ofPattern("d", Locale.getDefault())
    val monthFormatter = DateTimeFormatter.ofPattern("MMM", Locale.getDefault())
    val monthYearFormatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.getDefault())

    return when {
        // Same month & same year → "5 - 10 September"
        checkIn.month == checkOut.month && checkIn.year == checkOut.year -> {
            "${checkIn.format(dayFormatter)} - ${checkOut.format(dayFormatter)} ${checkIn.format(monthFormatter)}"
        }

        // Different months but same year → "28 September - 2 October"
        checkIn.year == checkOut.year -> {
            "${checkIn.format(dayFormatter)} ${checkIn.format(monthFormatter)} - " +
                    "${checkOut.format(dayFormatter)} ${checkOut.format(monthFormatter)}"
        }

        // Different years → "30 December 2025 - 2 January 2026"
        else -> {
            "${checkIn.format(dayFormatter)} ${checkIn.format(monthYearFormatter)} - " +
                    "${checkOut.format(dayFormatter)} ${checkOut.format(monthYearFormatter)}"
        }
    }
}

@Composable
fun formatDuration(start: LocalDate, end: LocalDate): String {
    val context = LocalContext.current
    val days = ChronoUnit.DAYS.between(start, end)
    val months = ChronoUnit.MONTHS.between(start, end)
    val years = ChronoUnit.YEARS.between(start, end)
    return when {
        days < 7 -> "$days ${context.resources.getQuantityString(R.plurals.numberOfDay, days.toInt())}"
        months < 1 -> {
            val weeks = days / 7
            "$weeks ${context.resources.getQuantityString(R.plurals.numberOfWeek, weeks.toInt())}"
        }
        years < 1 -> "$months ${context.resources.getQuantityString(R.plurals.numberOfMonth, months.toInt())}"
        else -> "$years ${context.resources.getQuantityString(R.plurals.numberOfYear, years.toInt())}"
    }
}
