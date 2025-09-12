package com.demobnb.propertylisting.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.demobnb.propertylisting.R

data class Highlight(@DrawableRes val iconResId: Int, @StringRes val titleResId: Int, @StringRes val descriptionResId: Int)

object HighlightConstants {
    val highlightsRepo: HashMap<Int, Highlight> = hashMapOf(
        1 to Highlight(R.drawable.outline_city_view_24, R.string.city_view, R.string.city_view_des),
        2 to Highlight(R.drawable.outline_dive_right_in_24, R.string.dive_right_in, R.string.dive_right_in_des),
        3 to Highlight(R.drawable.outline_extra_spacious_24, R.string.extra_spacious, R.string.extra_spacious_des),
        4 to Highlight(R.drawable.outline_self_checkin_24, R.string.self_check_in, R.string.self_check_in_des),
        5 to Highlight(R.drawable.outline_peace_and_quiet_24, R.string.peace_and_quiet, R.string.peace_and_quiet_des)
    )
}
