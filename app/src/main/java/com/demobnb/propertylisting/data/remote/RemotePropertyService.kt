package com.demobnb.propertylisting.data.remote

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import com.demobnb.propertylisting.repo.PropertyService
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Defines the remote service interface for accessing property-related data.
 * This interface is implemented by Retrofit to make network requests.
 */
interface RemotePropertyService : PropertyService {
    @GET("properties")
    override suspend fun fetchProperties(): List<PropertySummary>

    @GET("properties/{id}")
    override suspend fun fetchPropertyDetails(@Path("id") id: Long): PropertyDetail

    @GET("user/{id}")
    override suspend fun fetchUser(@Path("id") id: Long): User

    @GET("reviews/{propertyId}")
    override suspend fun fetchReviews(@Path("propertyId") propertyId: Long): List<Review>
}