package com.demobnb.propertylisting.repo

import androidx.lifecycle.liveData
import com.demobnb.propertylisting.data.local.PropertySummaryDao
import com.demobnb.propertylisting.di.Local
import com.demobnb.propertylisting.di.Remote
import com.demobnb.propertylisting.model.DataSource
import com.demobnb.propertylisting.model.PropertyDetail
import com.demobnb.propertylisting.model.PropertySummary
import com.demobnb.propertylisting.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface PropertyRepository {
    fun fetchPropertiesList(): Flow<Resource<List<PropertySummary>>>
    fun fetchPropertyDetails(id: Long): Flow<Resource<PropertyDetail>>
}
class PropertyRepositoryImpl @Inject constructor(
    @Remote private val remoteService: PropertyService,
    @Local private val localService: PropertyService,
    private val propertySummaryDao: PropertySummaryDao
) : PropertyRepository  {

    fun <T> fetch(local: suspend () -> T, remote: suspend () -> T, updateLocal: suspend (T) -> Unit) : Flow<Resource<T>> = flow {
        emit(Resource.Loading(source = DataSource.LOCAL))
        val resourceLocalProperties = try {
            Resource.Success(local(), DataSource.LOCAL)
        } catch (e: Exception) {
            Resource.Error(e, DataSource.LOCAL)
        }
        emit(resourceLocalProperties)

        emit(Resource.Loading(source = DataSource.REMOTE))
        val resourceRemoteProperties = try {
            Resource.Success(remote(), DataSource.REMOTE)
        } catch (e: Exception) {
            Resource.Error(e, DataSource.REMOTE)
        }


        if (resourceLocalProperties is Resource.Success && resourceRemoteProperties is Resource.Success) {
            if (resourceLocalProperties.data != resourceRemoteProperties.data) {
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

        }
        )







}