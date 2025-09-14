package com.demobnb.propertylisting.util


fun Float.format(decimalCount: Int = 2): String {
    return String.format("%.${decimalCount}f", this)
}