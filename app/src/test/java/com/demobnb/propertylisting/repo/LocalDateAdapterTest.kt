package com.demobnb.propertylisting.repo

import org.junit.Test

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import com.google.gson.annotations.JsonAdapter // For testing with data class
import org.junit.Assert.*
import org.junit.Before
import java.time.LocalDate
import java.time.Month

class LocalDateAdapterTest {

    private lateinit var gson: Gson
    private lateinit var directAdapter: LocalDateAdapter // For direct method calls

    @Before
    fun setUp() {
        directAdapter = LocalDateAdapter()
        gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .create()
    }

    // --- Test Deserialization ---

    @Test
    fun `deserialize ISO_LOCAL_DATE string correctly`() {
        val json = "\"2023-10-27\"" // JSON strings are quoted
        val expectedDate = LocalDate.of(2023, Month.OCTOBER, 27)
        val actualDate = gson.fromJson(json, LocalDate::class.java)
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `deserialize ISO_OFFSET_DATE_TIME string correctly (extracts date)`() {
        val json = "\"2023-11-15T10:30:00+02:00\""
        val expectedDate = LocalDate.of(2023, Month.NOVEMBER, 15)
        val actualDate = gson.fromJson(json, LocalDate::class.java)
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `deserialize ISO_ZONED_DATE_TIME string correctly (extracts date)`() {
        val json = "\"2024-01-20T14:45:00-05:00[America/New_York]\""
        val expectedDate = LocalDate.of(2024, Month.JANUARY, 20)
        val actualDate = gson.fromJson(json, LocalDate::class.java)
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `deserialize custom format 'MM_dd_yyyy' string correctly`() {
        // Assuming your LocalDateAdapter includes DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val json = "\"12/25/2023\""
        val expectedDate = LocalDate.of(2023, Month.DECEMBER, 25)
        val actualDate = gson.fromJson(json, LocalDate::class.java)
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `deserialize another custom format 'yyyy-MM-dd HH_mm_ss' string correctly (extracts date)`() {
        // Assuming your LocalDateAdapter includes DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val json = "\"2023-03-10 14:30:55\""
        val expectedDate = LocalDate.of(2023, Month.MARCH, 10)
        val actualDate = gson.fromJson(json, LocalDate::class.java)
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `deserialize handles null json input`() {
        val json = "null"
        val actualDate = gson.fromJson(json, LocalDate::class.java)
        assertNull(actualDate)
    }

    @Test(expected = JsonParseException::class)
    fun `deserialize throws JsonParseException for unparseable date string`() {
        val json = "\"invalid-date-format\""
        gson.fromJson(json, LocalDate::class.java) // This should throw
    }

    @Test(expected = JsonParseException::class)
    fun `deserialize throws JsonParseException for wrongly formatted known pattern`() {
        val json = "\"2023/10/27\"" // Wrong separator for ISO_LOCAL_DATE
        gson.fromJson(json, LocalDate::class.java)
    }

    // --- Test Serialization ---

    @Test
    fun `serialize LocalDate correctly to ISO_LOCAL_DATE string`() {
        val date = LocalDate.of(2023, Month.SEPTEMBER, 5)
        val expectedJson = "\"2023-09-05\"" // Gson will add the quotes
        val actualJson = gson.toJson(date)
        assertEquals(expectedJson, actualJson)
    }

    @Test
    fun `serialize null LocalDate correctly to null`() {
        val date: LocalDate? = null
        val expectedJson = "null"
        val actualJson = gson.toJson(date)
        assertEquals(expectedJson, actualJson)
    }

    // --- Test with a Data Class ---

    data class TestEvent(@JsonAdapter(LocalDateAdapter::class) val eventDate: LocalDate?) // Can also test adapter directly on field if needed

    data class EventHolder(val name: String, val date: LocalDate?)

    @Test
    fun `deserialize correctly within a data class`() {
        val json = """{"name":"Birthday Party","date":"2024-07-19"}"""
        val expectedEventHolder = EventHolder("Birthday Party", LocalDate.of(2024, Month.JULY, 19))
        val actualEventHolder = gson.fromJson(json, EventHolder::class.java)
        assertEquals(expectedEventHolder, actualEventHolder)
    }

    @Test
    fun `serialize correctly from a data class`() {
        val eventHolder = EventHolder("Conference", LocalDate.of(2023, Month.NOVEMBER, 10))
        // Note: Field order in JSON isn't guaranteed unless specified or using specific Gson settings.
        // We'll check for the presence and value of the date.
        val expectedDateJsonPart = "\"date\":\"2023-11-10\""
        val actualJson = gson.toJson(eventHolder)
        assertTrue(actualJson.contains(expectedDateJsonPart))
        assertTrue(actualJson.contains("\"name\":\"Conference\""))
    }

    // --- Direct adapter method tests (optional, for more granular checks) ---
    @Test
    fun `direct deserialize ISO_LOCAL_DATE string correctly`() {
        val dateString = "2023-10-27"
        val jsonElement = gson.toJsonTree(dateString)
        val expectedDate = LocalDate.of(2023, Month.OCTOBER, 27)
        val actualDate = directAdapter.deserialize(jsonElement, LocalDate::class.java, null)
        assertEquals(expectedDate, actualDate)
    }

    @Test
    fun `direct serialize LocalDate correctly`() {
        val date = LocalDate.of(2023, Month.SEPTEMBER, 5)
        val expectedJsonPrimitive = gson.toJsonTree("2023-09-05")
        val actualJsonElement = directAdapter.serialize(date, LocalDate::class.java, null)
        assertEquals(expectedJsonPrimitive, actualJsonElement)
    }
}