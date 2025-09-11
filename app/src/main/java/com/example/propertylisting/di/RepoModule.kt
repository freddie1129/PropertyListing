package com.example.propertylisting.di

import com.example.propertylisting.repo.PropertyLocalService
import com.example.propertylisting.repo.PropertyRemoteService
import com.example.propertylisting.repo.PropertyRepository
import com.example.propertylisting.repo.PropertyRepositoryImpl
import com.example.propertylisting.repo.PropertyServiceApi
import dagger.Binds
import dagger.Module
import dagger.Provides
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