package com.example.simpleappdevelopmentusingtdd.presentation.repository

import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import kotlinx.coroutines.flow.Flow

class PlaylistRepositoryImpl : PlaylistRepository {
    override suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        TODO("Not yet implemented")
    }
}