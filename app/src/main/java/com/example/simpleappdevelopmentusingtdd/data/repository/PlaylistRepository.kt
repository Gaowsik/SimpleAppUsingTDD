package com.example.simpleappdevelopmentusingtdd.data.repository

import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>>

}