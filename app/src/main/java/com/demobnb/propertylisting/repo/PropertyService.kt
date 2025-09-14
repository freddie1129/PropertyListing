package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import retrofit2.http.Path

interface PropertyService {

    suspend fun fetchProperties(): List<PropertySummary>

    suspend fun fetchPropertyDetails(id: Long): PropertyDetail?

    suspend fun fetchUser(id: Long): User?

    suspend fun fetchReviews(propertyId: Long): List<Review>
}


