package com.demobnb.propertylisting.model

import com.demobnb.propertylisting.R

/**
 * [HighlightConstants] is an object that contains a repository of highlights that can be used in the app.
 * The highlights are stored in a [HashMap] where the key is an [Int] and the value is a [Highlight] object.
 * The [Highlight] object contains the drawable resource id, the string resource id for the title, and the string resource id for the description.
 */
object HighlightConstants {
    val highlightsRepo: HashMap<Int, Highlight> = hashMapOf(
        1 to Highlight(R.drawable.outline_city_view_24, R.string.city_view, R.string.city_view_des),
        2 to Highlight(
            R.drawable.outline_dive_right_in_24,
            R.string.dive_right_in,
            R.string.dive_right_in_des
        ),
        3 to Highlight(
            R.drawable.outline_extra_spacious_24,
            R.string.extra_spacious,
            R.string.extra_spacious_des
        ),
        4 to Highlight(
            R.drawable.outline_self_checkin_24,
            R.string.self_check_in,
            R.string.self_check_in_des
        ),
        5 to Highlight(
            R.drawable.outline_peace_and_quiet_24,
            R.string.peace_and_quiet,
            R.string.peace_and_quiet_des
        )
    )
}