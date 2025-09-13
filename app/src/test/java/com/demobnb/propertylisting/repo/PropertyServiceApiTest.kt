package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class PropertyServiceApiTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: PropertyService // Your Retrofit service interface

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start() // Start the server before each test

        // Configure Gson with your LocalDateAdapter
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .create()

        // Configure Retrofit to use the MockWebServer's URL and custom Gson
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Use the mock server's URL
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PropertyService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown() // Shut down the server after each test
    }

    @Test
    fun `fetchProperties successful response`() =
        runBlocking { // Use runBlocking for suspend functions
            // 1. Prepare the mock response
            val checkInDateString = "2025-12-01"
            val checkOutDateString = "2025-11-20"
            val mockJsonResponse = """
           [ {
  "checkInDate" : "$checkInDateString",
  "checkOutDate" : "$checkOutDateString",
  "extendedPrice" : 73.81555,
  "featureImage" : "https://picsum.photos/200",
  "id" : 1,
  "rate" : 1.2487566,
  "title" : "cellar"
}, {
  "checkInDate" : "$checkInDateString",
  "checkOutDate" : "$checkOutDateString",
  "extendedPrice" : 93.8213,
  "featureImage" : "https://picsum.photos/200",
  "id" : 2,
  "rate" : 4.8179936,
  "title" : "hallway"
}]
        """.trimIndent()
            val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(mockJsonResponse)
            mockWebServer.enqueue(mockResponse) // Enqueue the response for the next request

            // 2. Make the API call
            val properties: List<PropertySummary> = apiService.fetchProperties()

            // 3. Assertions
            assertNotNull(properties)
            assertEquals(2, properties.size)
            assertEquals(1L, properties[0].id)
            assertEquals(LocalDate.parse(checkInDateString), properties[0].checkInDate)
            assertEquals(LocalDate.parse(checkOutDateString), properties[0].checkOutDate)

            // 4. Verify the request (optional but good practice)
            val recordedRequest = mockWebServer.takeRequest()
            assertEquals("GET", recordedRequest.method)
            assertEquals("/properties", recordedRequest.path) // Ensure the endpoint path is correct
        }

    @Test
    fun `fetchPropertyDetails successful response with LocalDate`() = runBlocking {
        val checkInDateString = "2023-12-01"
        val lastUpdatedString = "2023-11-20"
        val mockJsonResponse = """
            {
                "id": 101,
                "name": "Luxury Villa",
                "description": "A beautiful villa with a pool.",
                "checkInDate": "$checkInDateString",
                "lastUpdated": "$lastUpdatedString"
            }
        """.trimIndent()
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(mockJsonResponse)
        mockWebServer.enqueue(mockResponse)

        val propertyId = 101L
        val propertyDetail: PropertyDetail = apiService.fetchPropertyDetails(propertyId)

        assertNotNull(propertyDetail)
        assertEquals(propertyId, propertyDetail.id)
        //   assertEquals("Luxury Villa", propertyDetail.name)
        // assertEquals(LocalDate.parse(checkInDateString), propertyDetail.checkInDate)
        // assertEquals(LocalDate.parse(lastUpdatedString), propertyDetail.lastUpdated)

        val recordedRequest = mockWebServer.takeRequest()
        assertEquals("GET", recordedRequest.method)
        assertEquals("/properties/$propertyId", recordedRequest.path)
    }

    @Test
    fun `fetchProperties API error 404`() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(404)
            .setBody("""{"error": "Not Found"}""")
        mockWebServer.enqueue(mockResponse)

        var exceptionThrown = false
        try {
            apiService.fetchProperties()
        } catch (e: retrofit2.HttpException) {
            exceptionThrown = true
            assertEquals(404, e.code())
            // You can also inspect e.response()?.errorBody()?.string() if needed
        }
        assertTrue("HttpException was expected but not thrown", exceptionThrown)

        val recordedRequest = mockWebServer.takeRequest()
        assertEquals("/properties", recordedRequest.path)
    }


    @Test
    fun `fetchProperties network error`() { // No runBlocking needed if not calling suspend directly in assertion block
        // Simulate a network error (e.g., by not starting the server or causing an IOException)
        // For MockWebServer, you can also enqueue a response that simulates a network problem.
        // Or, more simply, shut down the server before the call for some types of errors.
        mockWebServer.shutdown() // Simulate server not reachable for this specific test

        var exceptionThrown = false
        try {
            runBlocking { apiService.fetchProperties() }
        } catch (e: java.io.IOException) { // Or a more specific network exception
            exceptionThrown = true
        }
        assertTrue("IOException or similar network error was expected", exceptionThrown)
        // Note: After this test, mockWebServer is down. setUp will restart it for the next test.
    }

    // Add more tests for:
    // - Different HTTP error codes (500, 401, 403, etc.)
    // - Empty responses (e.g., empty list for fetchProperties)
    // - Malformed JSON responses (to test Gson parsing robustness, though often your adapter handles this)
    // - Requests with headers (if your API needs them)
    // - Requests with query parameters
    // - POST/PUT requests with bodies
}
