package com.demobnb.propertylisting

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.demobnb.propertylisting.R // Assuming R class is accessible
import com.demobnb.propertylisting.extension.CurrencyProvider
import com.demobnb.propertylisting.extension.format // Import your extension function
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.ui.view.PropertySummaryView
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.atomic.AtomicBoolean

class PropertySummaryViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context
    private lateinit var sampleProperty: PropertySummary

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        // Use a consistent PropertySummary object for tests
        sampleProperty = MockData.generateProperties(1)[0]
        // Ensure sampleProperty has values that make sense for testing
        // For example, explicitly set a known title, price, rate if MockData is too random
        // sampleProperty = sampleProperty.copy(title = "Test Beach House", extendedPrice = 1250.75f, rate = 4.7f, duration = 5)
    }

    @Test
    fun propertySummaryView_displaysCorrectTitle() {
        composeTestRule.setContent {
            PropertySummaryView(property = sampleProperty, onClick = {})
        }

        composeTestRule.onNodeWithText(sampleProperty.title)
            .assertIsDisplayed()
    }


    @Test
    fun propertySummaryView_displaysCorrectRateAndStarIcon() {
        composeTestRule.setContent {
            PropertySummaryView(property = sampleProperty, onClick = {})
        }

        // Check for the star icon (by its content description)
        composeTestRule.onNodeWithContentDescription("Star")
            .assertIsDisplayed()

        // Check for the formatted rate text
        composeTestRule.onNodeWithText(sampleProperty.rate.format())
            .assertIsDisplayed()
    }





}