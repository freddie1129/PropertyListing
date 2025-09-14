package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Resource
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for accessing property-related data.
 *
 * This interface defines the contract for fetching property listings, details, user information, and reviews.
 * Implementations of this interface will handle the actual data retrieval from sources like a network API or a local database.
 */
interface PropertyRepository {
    fun fetchPropertiesList(): Flow<Resource<List<PropertySummary>>>
    fun fetchPropertyDetails(id: Long): Flow<Resource<PropertyDetail>>

    fun fetchUser(id: Long): Flow<Resource<User>>

    fun fetchReviews(propertyId: Long): Flow<Resource<List<Review>>>
}