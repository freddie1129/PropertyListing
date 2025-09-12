package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary

interface PropertyServiceApi {
    suspend fun fetchProperties(): List<PropertySummary>
    suspend fun fetchPropertyDetails(id: Long): PropertyDetail
}


