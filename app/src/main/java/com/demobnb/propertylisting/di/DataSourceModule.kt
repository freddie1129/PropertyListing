package com.demobnb.propertylisting.di

import com.demobnb.propertylisting.repo.LocalDateAdapter
import com.demobnb.propertylisting.repo.RemotePropertyService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
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






        private const val BASE_URL = "https://freddie1129.github.io/"

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter()) // <--- REGISTER HERE
            // Add other type adapters if needed (e.g., for LocalDateTime, ZonedDateTime)
            .create()
    }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

     //   @Remote
        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): RemotePropertyService {
            return   retrofit.create(RemotePropertyService::class.java)
        }

  /*  @Local
    @Provides
    @Singleton
     fun provideLocalDataSource(impl: PropertyLocalService): PropertyServiceApi {
         return impl
     }

    @Remote
    @Provides
    @Singleton
    fun provideApiService(impl: PropertyRemoteService): PropertyServiceApi {
        return   impl
    }*/





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