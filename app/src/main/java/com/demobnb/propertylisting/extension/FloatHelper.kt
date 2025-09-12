package com.demobnb.propertylisting.extension


fun Float.format(decimalCount: Int = 2) : String {
    return String.format("%.${decimalCount}f", this)
}