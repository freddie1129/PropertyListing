package com.demobnb.propertylisting

import com.demobnb.propertylisting.ui.view.HostView



import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.Host
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * [HostViewTest] is a test class for the [HostView] composable.
 *
 * The tests cover:
 * - Displaying the correct host name.
 * - Displaying the host avatar with the correct content description.
 * - Displaying correct hosting details when the hosting duration is in years.
 * - Displaying correct hosting details when the hosting duration is in months.
 *
 */
class HostViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context
    private lateinit var host: Host
    private lateinit var hostWith3YearHosting: Host
    private lateinit var hostWith6MonthHosting: Host

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        host = MockData.host
        hostWith3YearHosting = MockData.hostWith3YearHosting
        hostWith6MonthHosting = MockData.hostWith6MonthHosting
    }

    @Test
    fun hostView_displaysCorrectHostName() {
        // Given
        val expectedHostNameText = context.getString(R.string.hostedBy, host.name)


        composeTestRule.setContent {
            HostView(host = host)
        }


        composeTestRule.onNodeWithText(expectedHostNameText)
            .assertIsDisplayed()
    }

    @Test
    fun hostView_displaysHostAvatar_withCorrectContentDescription() {

        val expectedContentDescription = host.name

        composeTestRule.setContent {
            HostView(host = host)
        }

        composeTestRule.onNodeWithContentDescription(expectedContentDescription)
            .assertIsDisplayed()
    }

    @Test
    fun hostView_displaysCorrectHostingDetails_forYears() {
        // Given
        val years = 3
        val yearText = context.resources.getQuantityString(R.plurals.numberOfYear, years)
        val expectedHostingDetailText =
            "${hostWith3YearHosting.feature} • $years $yearText ${context.getString(R.string.hosting)}"

        // When
        composeTestRule.setContent {
            HostView(host = hostWith3YearHosting)
        }

        // Then
        composeTestRule.onNodeWithText(expectedHostingDetailText)
            .assertIsDisplayed()
    }

    @Test
    fun hostView_displaysCorrectHostingDetails_forMonths() {
        val months = 6
        val monthText = context.resources.getQuantityString(R.plurals.numberOfMonth, months)
        val expectedHostingDetailText =
            "${hostWith6MonthHosting.feature} • $months $monthText ${context.getString(R.string.hosting)}"

        // When
        composeTestRule.setContent {
            HostView(host = hostWith6MonthHosting)
        }

        // Then
        composeTestRule.onNodeWithText(expectedHostingDetailText)
            .assertIsDisplayed()
    }




}
