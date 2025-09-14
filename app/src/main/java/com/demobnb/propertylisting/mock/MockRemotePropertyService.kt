package com.demobnb.propertylisting.mock

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import com.demobnb.propertylisting.repo.PropertyService
import javax.inject.Inject

/**
 * A mock implementation of [com.demobnb.propertylisting.repo.PropertyService] that uses [MockData] to generate data.
 * This is useful for testing and development purposes.
 */
class MockRemotePropertyService @Inject constructor() : PropertyService {

    override suspend fun fetchProperties(): List<PropertySummary> {
        return MockData.generateProperties(20)
    }

    override suspend fun fetchPropertyDetails(id: Long): PropertyDetail? {
        return MockData.generatePropertyDetail(id = id)
    }

    override suspend fun fetchUser(id: Long): User? {
        return MockData.generateUser(id)
    }

    override suspend fun fetchReviews(propertyId: Long): List<Review> {
        return MockData.generateReviews(10)
    }

}