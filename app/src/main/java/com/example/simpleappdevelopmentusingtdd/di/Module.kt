package com.example.simpleappdevelopmentusingtdd.di

import android.content.Context
import com.example.simpleappdevelopmentusingtdd.data.repository.PlaylistRepository
import com.example.simpleappdevelopmentusingtdd.data.repository.PlaylistRepositoryImpl
import com.example.simpleappdevelopmentusingtdd.data.source.PlayListService
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
        @ApplicationContext context: Context,
        api: PlayListService
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(
            api
        )
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
    fun provideWMSApi(okHttpClient: OkHttpClient): PlayListService {
        val baseUrl = ""
        val wMSRoleApiClient = createAppApiClient(baseUrl, okHttpClient)
        return wMSRoleApiClient.create(PlayListService::class.java)
    }

}