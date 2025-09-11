package com.example.propertylisting.di

import com.example.propertylisting.repo.PropertyLocalService
import com.example.propertylisting.repo.PropertyRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Local

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Remote

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

/*    @Remote
    @Singleton
    @Provides
    fun provideLocalDataSource(): PropertyRemoteService = PropertyRemoteService()

    @Local
    @Singleton
    @Provides
    fun provideRemoteDataSource(): PropertyLocalService = PropertyLocalService()*/


  //  @Singleton
 ////   @Provides
 //   fun providePropertyRepository(local: PropertyLocalService, remote: PropertyRemoteService): PropertyRepository = PropertyRepository(remote, local)

}