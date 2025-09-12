package com.demobnb.propertylisting.di

import com.demobnb.propertylisting.repo.PropertyLocalService
import com.demobnb.propertylisting.repo.PropertyRemoteService
import com.demobnb.propertylisting.repo.PropertyRepository
import com.demobnb.propertylisting.repo.PropertyRepositoryImpl
import com.demobnb.propertylisting.repo.PropertyServiceApi
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
    abstract fun provideLocalDataSource(impl: PropertyLocalService): PropertyServiceApi


    @Remote
    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(impl: PropertyRemoteService): PropertyServiceApi

}