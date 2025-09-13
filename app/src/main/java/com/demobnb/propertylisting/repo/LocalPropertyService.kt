package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.data.local.PropertySummaryDao
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import javax.inject.Inject


class LocalPropertyService @Inject constructor(val propertySummaryDao: PropertySummaryDao) : PropertyService {

    override suspend fun fetchProperties(): List<PropertySummary> {
        return propertySummaryDao.getAll()
    }

    override suspend fun fetchPropertyDetails(id: Long): PropertyDetail {
        return MockData.generatePropertyDetail(id = id)
    }

}