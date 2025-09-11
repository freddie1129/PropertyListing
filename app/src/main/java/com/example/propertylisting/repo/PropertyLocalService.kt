package com.example.propertylisting.repo

import com.example.propertylisting.mock.MockData
import com.example.propertylisting.model.PropertyDetail
import com.example.propertylisting.model.PropertySummary
import javax.inject.Inject


class PropertyLocalService @Inject constructor() : PropertyServiceApi {

    override suspend fun fetchProperties(): List<PropertySummary> {
        return emptyList()  //MockData.generateProperties(7)
    }

    override suspend fun fetchPropertyDetails(id: Long): PropertyDetail {
        return MockData.generatePropertyDetail(id = id)
    }

}