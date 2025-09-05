package com.example.simpleappdevelopmentusingtdd.di

import android.content.Context
import com.example.simpleappdevelopmentusingtdd.presentation.repository.PlaylistRepository
import com.example.simpleappdevelopmentusingtdd.presentation.repository.PlaylistRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePlaylistRepository(
        @ApplicationContext context: Context
    ): PlaylistRepository {
        return PlaylistRepositoryImpl(

        )
    }

}