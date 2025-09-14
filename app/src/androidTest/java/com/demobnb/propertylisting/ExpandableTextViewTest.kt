package com.demobnb.propertylisting

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.demobnb.propertylisting.util.TestTags
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.ui.components.ExpandableTextView
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * [ExpandableTextViewTest] is a test class for the [ExpandableTextView] Composable.
 *
 * The tests cover:
 * - When the text is short (less than the collapsed line limit(5)).
 * - When the text is exactly at the collapsed line limit(5).
 * - When the text is long (exceeds the collapsed line limit(5)).
 * - Interaction with the "Show more" button and the resulting bottom sheet.
 * - Closing the bottom sheet.
 */
class ExpandableTextViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    private fun getString(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    private val shortText = MockData.shortSentence
    private val longText = MockData.longSentence
    private val exactlyFiveLines = "Line 1\nLine 2\nLine 3\nLine 4\nLine 5" // Assuming default maxLinesCollapsed = 5


    /**
     * Tests that the "Show more" button is not displayed when the text is less than 5 lines
     */
    @Test
    fun shortText_doesNotShow_showMoreButton() {
        composeTestRule.setContent {
            MaterialTheme { // Wrap with your app's theme or MaterialTheme
                ExpandableTextView(text = shortText)
            }
        }
        composeTestRule.onNodeWithText(shortText).assertIsDisplayed()
        composeTestRule.onNodeWithText("Show more").assertDoesNotExist()
    }

    /**
     * Tests that the "Show more" button is not displayed when the text has five lines
     */
    @Test
    fun exactlyFiveLines_doesNotShow_showMoreButton_ifNotOverflowing() {
        composeTestRule.setContent {
            MaterialTheme {
                ExpandableTextView(text = exactlyFiveLines)
            }
        }

        composeTestRule.onNodeWithText("Show more").assertDoesNotExist()

        composeTestRule.onNodeWithText(text = "Line 1", substring = true).assertIsDisplayed()
        composeTestRule.onNodeWithText(text = "Line 5", substring = true).assertIsDisplayed()
    }

    /**
     * Tests that the tap the "Show more" button to display the full text in a bottom sheet
     */
    @Test
    fun longText_shows_showMoreButton_andOpensBottomSheet_withFullText() {
        val showMoreButtonText = getString(R.string.show_more)
        val closeIconContentDescription = getString(R.string.close_bottom_sheet)
        composeTestRule.setContent {
            MaterialTheme {
                ExpandableTextView(text = longText)
            }
        }
        val showMoreButton = composeTestRule.onNodeWithText(showMoreButtonText)
        showMoreButton.assertIsDisplayed()
        showMoreButton.performClick()

        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithContentDescription(closeIconContentDescription)
                .fetchSemanticsNodes().size == 1
        }
        composeTestRule.onNodeWithContentDescription(closeIconContentDescription)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(TestTags.ExpandableTextView.bottomSheetContentText)
            .assertIsDisplayed()
            .assertTextEquals(longText)
    }

    /**
     * Tests that the tap the "Show more" button to display the full text in a bottom sheet.
     * Then tap close button to close the bottom sheet.
     */
    @Test
    fun longText_opensBottomSheet_andClosesIt_withCloseButton() {
        val closeIconContentDescription = getString(R.string.close_bottom_sheet)
        val showMoreButtonText = getString(R.string.show_more)
        composeTestRule.setContent {
            MaterialTheme {
                ExpandableTextView(text = longText)
            }
        }
        composeTestRule.onNodeWithText(showMoreButtonText).performClick()
        val closeButtonInSheet = composeTestRule.onNodeWithContentDescription(closeIconContentDescription)
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithContentDescription(closeIconContentDescription)
                .fetchSemanticsNodes().size == 1
        }
        closeButtonInSheet.assertIsDisplayed()
        closeButtonInSheet.performClick()
        composeTestRule.waitUntil(timeoutMillis = 5000) {
            composeTestRule.onAllNodesWithContentDescription(closeIconContentDescription)
                .fetchSemanticsNodes().isEmpty()
        }
        composeTestRule.onNodeWithText(showMoreButtonText).assertIsDisplayed()
    }

}

