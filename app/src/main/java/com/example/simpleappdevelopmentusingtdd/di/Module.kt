package com.example.simpleappdevelopmentusingtdd.di

import android.content.Context
import com.example.simpleappdevelopmentusingtdd.data.PlayListApi
import com.example.simpleappdevelopmentusingtdd.data.repository.PlaylistRepository
import com.example.simpleappdevelopmentusingtdd.data.repository.PlaylistRepositoryImpl
import com.example.simpleappdevelopmentusingtdd.data.source.PlayListDataSource
import com.example.simpleappdevelopmentusingtdd.data.source.PlayListDataSourceImpl
import com.example.simpleappdevelopmentusingtdd.data.util.Utill.createAppApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePlaylistRepository(
        playListDataSource: PlayListDataSource
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(
            playListDataSource
        )
    }

    @Singleton
    @Provides
    fun providePlaylistDataSource(
        api: PlayListApi,
    ): PlayListDataSource {
        return PlayListDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().readTimeout(5000, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor).connectTimeout(5000, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providePlayListApi(okHttpClient: OkHttpClient): PlayListApi {
        val baseUrl = "http://10.0.2.2:3000/"
        val wMSRoleApiClient = createAppApiClient(baseUrl, okHttpClient)
        return wMSRoleApiClient.create(PlayListApi::class.java)
    }

}