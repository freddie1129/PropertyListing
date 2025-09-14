package com.demobnb.propertylisting.repo

import androidx.lifecycle.liveData
import com.demobnb.propertylisting.data.local.PropertyDetailDao
import com.demobnb.propertylisting.data.local.PropertySummaryDao
import com.demobnb.propertylisting.data.local.ReviewDao
import com.demobnb.propertylisting.data.local.UserDao
import com.demobnb.propertylisting.di.Local
import com.demobnb.propertylisting.di.Remote
import com.demobnb.propertylisting.model.DataSource
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Resource
import com.demobnb.propertylisting.model.Review
import com.demobnb.propertylisting.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface PropertyRepository {
    fun fetchPropertiesList(): Flow<Resource<List<PropertySummary>>>
    fun fetchPropertyDetails(id: Long): Flow<Resource<PropertyDetail>>

    fun fetchUser(id: Long): Flow<Resource<User>>

    fun fetchReviews(propertyId: Long): Flow<Resource<List<Review>>>
}
class PropertyRepositoryImpl @Inject constructor(
    @Remote private val remoteService: PropertyService,
    @Local private val localService: PropertyService,
    private val propertySummaryDao: PropertySummaryDao,
    private val propertyDetailDao: PropertyDetailDao,
    private val userDao: UserDao,
    private val reviewDao: ReviewDao
) : PropertyRepository  {

    fun <T> fetch(local: suspend () -> T?, remote: suspend () -> T?, updateLocal: suspend (T) -> Unit) : Flow<Resource<T>> = flow {
        emit(Resource.Loading(source = DataSource.LOCAL))


        val resourceLocalProperties = try {
            local()?.let { data ->
                Resource.Success<T>(data, DataSource.LOCAL).also {
                  emit(it)
                }
            }
        } catch (e: Exception) {
            Resource.Error<T>(e, DataSource.LOCAL)
        }


        emit(Resource.Loading(source = DataSource.REMOTE))
        val resourceRemoteProperties = try {
            remote()?.let {
                Resource.Success<T>(it, DataSource.REMOTE)
            } ?: Resource.Error<T>(Exception("Empty response"), DataSource.REMOTE)
        } catch (e: Exception) {
            Resource.Error<T>(e, DataSource.REMOTE)
        }


        if (resourceRemoteProperties is Resource.Success) {
            if (resourceLocalProperties?.data != resourceRemoteProperties.data) {
                resourceRemoteProperties.data?.let { data  ->
                    updateLocal(data)
                }
                emit(resourceRemoteProperties)
            }
        } else if (resourceRemoteProperties is Resource.Error) {
            emit(resourceRemoteProperties)
        }
    }

    override fun fetchPropertiesList() = fetch<List<PropertySummary>>(local = {

     localService.fetchProperties() },
        remote = {
     remoteService.fetchProperties()
    },
        updateLocal = { newProperties ->
            propertySummaryDao.deleteAll()
            propertySummaryDao.insertAll(newProperties)
        }
        )

    override fun fetchPropertyDetails(id: Long) = fetch<PropertyDetail>(local = {
        localService.fetchPropertyDetails(id)
    }, remote = {
        remoteService.fetchPropertyDetails(id)

    },
        updateLocal = { data ->
            propertyDetailDao.insert(data)
        }
        )

    override fun fetchUser(id: Long): Flow<Resource<User>> = fetch<User>(local = {
        localService.fetchUser(id)
    }, remote = {
        remoteService.fetchUser(id)

    },
        updateLocal = { data ->
            userDao.insert(data)
        }
    )

    override fun fetchReviews(propertyId: Long): Flow<Resource<List<Review>>> = fetch<List<Review>>(
        local = {
            localService.fetchReviews(propertyId)
        },
        remote = {
            remoteService.fetchReviews(propertyId)
        },
        updateLocal = { data ->
            reviewDao.deleteReviewsByPropertyId(propertyId)
            reviewDao.insertAll(data)
        }
    )







}