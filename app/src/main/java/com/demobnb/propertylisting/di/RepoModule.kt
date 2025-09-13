package com.demobnb.propertylisting.di

import com.demobnb.propertylisting.repo.LocalPropertyService
import com.demobnb.propertylisting.repo.MockRemotePropertyService
import com.demobnb.propertylisting.repo.RemotePropertyService
import com.demobnb.propertylisting.repo.PropertyRepository
import com.demobnb.propertylisting.repo.PropertyRepositoryImpl
import com.demobnb.propertylisting.repo.PropertyService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindRepo(
        repoImpl: PropertyRepositoryImpl
    ): PropertyRepository

    @Local
    @Singleton
    @Binds
    abstract fun provideLocalDataSource(impl: LocalPropertyService): PropertyService


    @Remote
    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(impl: MockRemotePropertyService): PropertyService

}