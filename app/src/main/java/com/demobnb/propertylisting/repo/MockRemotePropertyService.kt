package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import kotlinx.coroutines.delay
import javax.inject.Inject

class MockRemotePropertyService @Inject constructor(): PropertyService {

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