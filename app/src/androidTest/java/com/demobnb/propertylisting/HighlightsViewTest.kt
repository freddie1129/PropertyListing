package com.demobnb.propertylisting

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.demobnb.propertylisting.model.HighlightConstants
import com.demobnb.propertylisting.ui.view.HighlightsView
import org.junit.Before
import org.junit.Rule
import org.junit.Test



/**
 * [HighlightsViewTest] is a test class for the [HighlightsView] Composable.
 *
 * The tests cover:
 * - Displaying highlights correctly when valid IDs are provided.
 * - Handling an empty list of highlight IDs gracefully.
 * - Handling a list containing invalid highlight IDs gracefully (e.g., displaying valid ones and ignoring invalid ones).
 *
 */
class HighlightsViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    // Helper function to get actual strings from resources
    private fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    @Test
    fun highlights_areDisplayedCorrectly_whenGivenValidIds() {
        // Given: A list of valid highlight IDs that exist in the repo
        val highlightIds = listOf(1, 2) // Make sure these IDs exist in HighlightConstants.highlightsRepo

        // Retrieve the expected highlight data
        val highlight1Data = HighlightConstants.highlightsRepo[highlightIds[0]]
        val highlight2Data = HighlightConstants.highlightsRepo[highlightIds[1]]

        // Ensure the test data is valid
        assert(highlight1Data != null) { "Test data invalid: Highlight ID ${highlightIds[0]} not found in repo." }
        assert(highlight2Data != null) { "Test data invalid: Highlight ID ${highlightIds[1]} not found in repo." }

        // When: The HighlightsView is composed with these IDs
        composeTestRule.setContent {
            HighlightsView(highlights = highlightIds)
        }

        // Then: Verify that the titles and descriptions for these highlights are displayed
        composeTestRule.onNodeWithText(getString(highlight1Data!!.titleResId))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(getString(highlight1Data.descriptionResId))
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(getString(highlight2Data!!.titleResId))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(getString(highlight2Data.descriptionResId))
            .assertIsDisplayed()
    }

    @Test
    fun highlights_handlesEmptyListGracefully() {
        // Given: An empty list of highlight IDs
        val highlightIds = emptyList<Int>()

        // When: The HighlightsView is composed with an empty list
        composeTestRule.setContent {
            HighlightsView(highlights = highlightIds)
        }

        // Then: Ensure no highlight text is displayed (or verify a specific "empty state" message if your UI has one)
        // This assumes that if the list is empty, no items from highlightsRepo will be looked up or displayed.
        // If your UI shows a specific message like "No highlights available", test for that text.

        // Example: Check that known text from valid highlights is NOT present
        val anyKnownHighlight = HighlightConstants.highlightsRepo.values.firstOrNull()
        if (anyKnownHighlight != null) {
            composeTestRule.onNodeWithText(getString(anyKnownHighlight.titleResId))
                .assertDoesNotExist()
            composeTestRule.onNodeWithText(getString(anyKnownHighlight.descriptionResId))
                .assertDoesNotExist()
        }
        // Add more assertions here if your view has a specific empty state UI
    }

    @Test
    fun highlights_handlesInvalidIdsGracefully() {
        // Given: A list containing an ID that does NOT exist in the repo
        val validId = 1 // Assuming 1 is a valid ID
        val invalidId = 999 // Assuming 999 is NOT a valid ID
        val highlightIds = listOf(validId, invalidId)

        val validHighlightData = HighlightConstants.highlightsRepo[validId]
        assert(validHighlightData != null) { "Test data invalid: Highlight ID $validId not found in repo." }
        assert(HighlightConstants.highlightsRepo[invalidId] == null) { "Test data invalid: Highlight ID $invalidId unexpectedly found in repo."}


        // When: The HighlightsView is composed
        composeTestRule.setContent {
            HighlightsView(highlights = highlightIds)
        }

        // Then: Verify that the valid highlight is displayed
        composeTestRule.onNodeWithText(getString(validHighlightData!!.titleResId))
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(getString(validHighlightData.descriptionResId))
            .assertIsDisplayed()
    }
}