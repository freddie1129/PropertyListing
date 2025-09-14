package com.demobnb.propertylisting.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Highlight(
    @DrawableRes val iconResId: Int,
    @StringRes val titleResId: Int,
    @StringRes val descriptionResId: Int
)

