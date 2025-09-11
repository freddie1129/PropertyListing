package com.example.propertylisting.repo

import com.example.propertylisting.di.Local
import com.example.propertylisting.di.Remote
import com.example.propertylisting.model.DataSource
import com.example.propertylisting.model.PropertyDetail
import com.example.propertylisting.model.PropertySummary
import com.example.propertylisting.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


interface PropertyRepository {
    fun fetchPropertiesList(): Flow<Resource<List<PropertySummary>>>
    fun fetchPropertyDetails(id: Long): Flow<Resource<PropertyDetail>>
}
class PropertyRepositoryImpl @Inject constructor(
    @Remote private val remoteService: PropertyServiceApi,
    @Local private val localService: PropertyServiceApi
) : PropertyRepository  {

    fun <T> fetch(local: suspend () -> T, remote: suspend () -> T) : Flow<Resource<T>> = flow {
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
    })

    override fun fetchPropertyDetails(id: Long) = fetch<PropertyDetail>(local = {
        localService.fetchPropertyDetails(id)
    }, remote = {
        remoteService.fetchPropertyDetails(id)
    })







}