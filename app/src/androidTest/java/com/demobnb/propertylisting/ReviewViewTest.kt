package com.demobnb.propertylisting



import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.demobnb.propertylisting.util.format // Import your extension function
import com.demobnb.propertylisting.ui.components.ReviewSummaryView
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * [ReviewViewTest] is a test class for the [ReviewSummaryView] composable.
 *
 * The tests cover:
 * - Displaying the correct average rating and the corresponding number of star icons.
 * - Displaying the correct review count.
 * - Displaying the correct plural string for "review" based on the review count (singular vs. plural).
 *
 */
class ReviewViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    private fun getPluralString(resId: Int, quantity: Int, vararg formatArgs: Any): String {
        return context.resources.getQuantityString(resId, quantity, *formatArgs)
    }

    @Test
    fun reviewView_displaysCorrectAverageRateAndStars() {
        val averageRate = 8.7f
        val reviewStandout = "Guest favourite"
        val reviewCount = 125

        val expectedFormattedRate = averageRate.format()
        val expectedStarCount = averageRate.toInt()

        composeTestRule.setContent {
            ReviewSummaryView(
                averageRate = averageRate,
                reviewStandout = reviewStandout,
                reviewCount = reviewCount
            )
        }


        composeTestRule.onNodeWithText(expectedFormattedRate)
            .assertIsDisplayed()


        composeTestRule.onAllNodesWithContentDescription("Star")
            .assertCountEquals(expectedStarCount)
            .fetchSemanticsNodes().forEach {
                assert(it.layoutInfo.isPlaced)
            }
    }



    @Test
    fun reviewView_displaysCorrectReviewCountAndPluralString_singular() {
        val averageRate = 7.5f
        val reviewStandout = "Clean Place"
        val reviewCount = 1

        val expectedReviewCountText = reviewCount.toString()
        val expectedReviewPluralString = getPluralString(R.plurals.numberOfReview, reviewCount)

        composeTestRule.setContent {
            ReviewSummaryView(
                averageRate = averageRate,
                reviewStandout = reviewStandout,
                reviewCount = reviewCount
            )
        }

        composeTestRule.onNodeWithText(expectedReviewCountText)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(expectedReviewPluralString)
            .assertIsDisplayed()
    }

    @Test
    fun reviewView_displaysCorrectReviewCountAndPluralString_plural() {
        val averageRate = 8.0f
        val reviewStandout = "Great Value"
        val reviewCount = 200

        val expectedReviewCountText = reviewCount.toString()
        val expectedReviewPluralString = getPluralString(R.plurals.numberOfReview, reviewCount)

        composeTestRule.setContent {
            ReviewSummaryView(
                averageRate = averageRate,
                reviewStandout = reviewStandout,
                reviewCount = reviewCount
            )
        }

        composeTestRule.onNodeWithText(expectedReviewCountText)
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(expectedReviewPluralString)
            .assertIsDisplayed()
    }

}

