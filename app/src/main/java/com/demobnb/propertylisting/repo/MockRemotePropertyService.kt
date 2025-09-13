package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import kotlinx.coroutines.delay
import javax.inject.Inject

class MockRemotePropertyService @Inject constructor(): PropertyService {

    override suspend fun fetchProperties(): List<PropertySummary> {
        delay(5000)
        val a = MockData.generateProperties(20)
      //  GsonBuilder().setPrettyPrinting().create().toJson(a)
        return a
    }

    override suspend fun fetchPropertyDetails(id: Long): PropertyDetail {
        return MockData.generatePropertyDetail(id = id)
    }

}