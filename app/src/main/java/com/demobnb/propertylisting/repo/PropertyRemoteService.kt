package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import kotlinx.coroutines.delay
import javax.inject.Inject

class PropertyRemoteService @Inject constructor(): PropertyServiceApi {

    override suspend fun fetchProperties(): List<PropertySummary> {
        delay(2000)
        return MockData.generateProperties(15)
    }

    override suspend fun fetchPropertyDetails(id: Long): PropertyDetail {
        return MockData.generatePropertyDetail(id = id)
    }

}