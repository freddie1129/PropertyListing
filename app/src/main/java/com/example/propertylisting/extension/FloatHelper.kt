package com.example.propertylisting.extension


fun Float.format(decimalCount: Int = 2) : String {
    return String.format("%.${decimalCount}f", this)
}