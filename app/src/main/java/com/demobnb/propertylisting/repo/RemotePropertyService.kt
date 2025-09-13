package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import retrofit2.http.GET
import retrofit2.http.Path

interface RemotePropertyService: PropertyService {
    @GET("mockdata/properties.json")
    override suspend fun fetchProperties(): List<PropertySummary>
    @GET("properties/{id}")
    override suspend fun fetchPropertyDetails(@Path("id") id: Long): PropertyDetail
}