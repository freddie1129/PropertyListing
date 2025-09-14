package com.demobnb.propertylisting.data.local

import com.demobnb.propertylisting.data.local.dao.PropertyDetailDao
import com.demobnb.propertylisting.data.local.dao.PropertySummaryDao
import com.demobnb.propertylisting.data.local.dao.ReviewDao
import com.demobnb.propertylisting.data.local.dao.UserDao
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import com.demobnb.propertylisting.repo.PropertyService
import javax.inject.Inject

/**
 * A local implementation of the [PropertyService] interface that interacts with Room DAOs
 * to retrieve property-related data.
 *
 * This class is responsible for fetching property summaries, property details, user information,
 * and reviews from the local database.
 *
 * @property propertySummaryDao DAO for accessing property summary data.
 * @property propertyDetailDao DAO for accessing property detail data.
 * @property userDao DAO for accessing user data.
 * @property reviewDao DAO for accessing review data.
 */
class LocalPropertyService @Inject constructor(
    val propertySummaryDao: PropertySummaryDao,
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