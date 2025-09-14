package com.demobnb.propertylisting.util

import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class FormatStayRangeTest {

    @Test
    fun `same month and year should show only days and month`() {
        val checkIn = LocalDate.of(2025, 10, 5)
        val checkOut = LocalDate.of(2025, 10, 10)

        val result = formatStayRange(checkIn, checkOut)

        assertEquals("5 - 10 Oct", result)
    }

    @Test
    fun `different months same year should show both months`() {
        val checkIn = LocalDate.of(2025, 10, 28)
        val checkOut = LocalDate.of(2025, 11, 2)

        val result = formatStayRange(checkIn, checkOut)

        assertEquals("28 Oct - 2 Nov", result)
    }

    @Test
    fun `different years should show both months with years`() {
        val checkIn = LocalDate.of(2025, 12, 30)
        val checkOut = LocalDate.of(2026, 1, 2)

        val result = formatStayRange(checkIn, checkOut)

        assertEquals("30 Dec 2025 - 2 Jan 2026", result)
    }

}