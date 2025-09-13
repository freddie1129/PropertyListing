package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import retrofit2.http.Path

interface PropertyService {
    //@GET("properties")
    suspend fun fetchProperties(): List<PropertySummary>
   // @GET("properties/{id}")
    suspend fun fetchPropertyDetails(@Path("id") id: Long): PropertyDetail
}


