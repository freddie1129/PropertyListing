package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User

/**
 * Interface for accessing property and related data.
 * This service provides methods to fetch property listings, details, user information, and reviews.
 */
interface PropertyService {

    suspend fun fetchProperties(): List<PropertySummary>

    suspend fun fetchPropertyDetails(id: Long): PropertyDetail?

    suspend fun fetchUser(id: Long): User?

    suspend fun fetchReviews(propertyId: Long): List<Review>
}


