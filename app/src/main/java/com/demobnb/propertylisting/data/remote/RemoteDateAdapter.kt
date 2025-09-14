package com.demobnb.propertylisting.data.remote

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

/**
 * Gson adapter for serializing and deserializing [LocalDate] objects.
 *
 * Handles multiple date formats returned by APIs and ensures consistent
 * serialization back to JSON.
 */
class RemoteDateAdapter : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    // Define the expected date formats from your API.
    // Order matters: Gson will try them in this order.
    // Add all formats your API might send.
    private val formatters = listOf(
        DateTimeFormatter.ISO_LOCAL_DATE, // Standard YYYY-MM-DD (e.g., "2023-10-27")
        DateTimeFormatter.ISO_OFFSET_DATE_TIME, // Includes offset and time (e.g., "2023-10-27T10:15:30+01:00")
        DateTimeFormatter.ISO_ZONED_DATE_TIME,  // Includes zone and time (e.g., "2023-10-27T10:15:30+01:00[Europe/Paris]")
        DateTimeFormatter.BASIC_ISO_DATE, // YYYYMMDD (e.g., "20231027")
        DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        ), // Example custom format
        DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.getDefault()) // Another example
        // Add more DateTimeFormatter instances if your API uses other formats
    )

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDate? {
        val dateString = json?.asString ?: return null

        for (formatter in formatters) {
            try {
                // If the format includes time/offset, parse to a more general temporal accessor
                // and then convert to LocalDate. This handles cases where the API sends a full timestamp
                // but you only care about the date part.
                if (formatter == DateTimeFormatter.ISO_OFFSET_DATE_TIME ||
                    formatter == DateTimeFormatter.ISO_ZONED_DATE_TIME ||
                    formatter.toString().contains("HH:mm")
                ) { // Crude check for time component
                    return LocalDate.from(formatter.parse(dateString))
                }
                return LocalDate.parse(dateString, formatter)
            } catch (e: DateTimeParseException) {
                // Try the next format
            }
        }
        // If no format matches, you might want to throw an exception or return null
        // depending on how strict you want to be.
        throw JsonParseException(
            "Unparseable date: \"$dateString\". Supported formats: ${
                formatters.joinToString { it.toString() }
            }"
        )
        // return null // Or return null if you want to allow missing/malformed dates gracefully
    }

    override fun serialize(
        src: LocalDate?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement? {
        // Choose the format you want to send TO the server (usually ISO_LOCAL_DATE)
        return src?.let { JsonPrimitive(it.format(DateTimeFormatter.ISO_LOCAL_DATE)) }
    }
}