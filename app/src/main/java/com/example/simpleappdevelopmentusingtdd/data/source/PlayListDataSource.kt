package com.example.simpleappdevelopmentusingtdd.data.source

import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import kotlinx.coroutines.flow.Flow

interface PlayListDataSource {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>>
}