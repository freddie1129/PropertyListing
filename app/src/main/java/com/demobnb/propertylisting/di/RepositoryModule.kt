package com.demobnb.propertylisting.di

import com.demobnb.propertylisting.data.local.LocalPropertyService
import com.demobnb.propertylisting.mock.MockRemotePropertyService
import com.demobnb.propertylisting.repo.PropertyRepository
import com.demobnb.propertylisting.repo.PropertyRepositoryImpl
import com.demobnb.propertylisting.repo.PropertyService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Dagger Hilt module that provides repository and data source dependencies.
 *
 * This module is installed in the [SingletonComponent], meaning the provided
 * dependencies will have a singleton scope and be available throughout the application.
 *
 * It uses `@Binds` to provide concrete implementations for interfaces:
 * - `PropertyRepositoryImpl` for `PropertyRepository`.
 * - `LocalPropertyService` for `PropertyService` (qualified with `@Local`).
 * - `MockRemotePropertyService` for `PropertyService` (qualified with `@Remote`).
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(
        impl: PropertyRepositoryImpl
    ): PropertyRepository

    @Local
    @Singleton
    @Binds
    abstract fun bindLocalDataSource(impl: LocalPropertyService): PropertyService

    // Inject MockRemotePropertyService is only for testing
    // Inject RemotePropertyService when deploy
    @Remote
    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(impl: MockRemotePropertyService): PropertyService


}