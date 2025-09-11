package com.example.propertylisting.model

enum class FacilityType {
    HairDryer,
    CleaningProducts,
    Shampoo
}

enum class FacilityCategory {
    ScenicViews,
    Bathroom,
    BedroomLaundry
}

data class Facility(
    val type: FacilityType,
    val category: FacilityCategory,
    val description: String
)
