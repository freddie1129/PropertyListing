package com.demobnb.propertylisting.repo

import com.demobnb.propertylisting.data.local.PropertyDetailDao
import com.demobnb.propertylisting.data.local.PropertySummaryDao
import com.demobnb.propertylisting.data.local.ReviewDao
import com.demobnb.propertylisting.data.local.UserDao
import com.demobnb.propertylisting.mock.MockData
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import javax.inject.Inject


class LocalPropertyService @Inject constructor(val propertySummaryDao: PropertySummaryDao,
                                               val propertyDetailDao: PropertyDetailDao,
                                               val userDao: UserDao,
                                               val reviewDao: ReviewDao
    ) : PropertyService {

    override suspend fun fetchProperties(): List<PropertySummary> {
        return propertySummaryDao.getAll()
    }

    override suspend fun fetchPropertyDetails(id: Long): PropertyDetail? {

        return propertyDetailDao.getPropertyDetailById(id)
    }

    override suspend fun fetchUser(id: Long): User? {
        return userDao.getUserById(id)
    }

    override suspend fun fetchReviews(propertyId: Long): List<Review> {
        return reviewDao.getReviewsByPropertyId(propertyId)
    }

}