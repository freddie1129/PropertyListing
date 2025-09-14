package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface RemotePropertyService: PropertyService {
    @GET("mockdata/properties.json")
    override suspend fun fetchProperties(): List<PropertySummary>

    @GET("properties/{id}")
    override suspend fun fetchPropertyDetails(@Path("id") id: Long): PropertyDetail

    @GET("user/{id}")
    override suspend fun fetchUser(@Path("id") id: Long): User

    @GET("reviews/{propertyId}")
    override suspend fun fetchReviews(@Path("propertyId") propertyId: Long): List<Review>
}