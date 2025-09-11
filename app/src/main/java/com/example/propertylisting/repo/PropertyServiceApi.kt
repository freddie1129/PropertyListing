package com.example.propertylisting.repo

import com.example.propertylisting.model.PropertyDetail
import com.example.propertylisting.model.PropertySummary

interface PropertyServiceApi {
    suspend fun fetchProperties(): List<PropertySummary>
    suspend fun fetchPropertyDetails(id: Long): PropertyDetail
}


