package com.example.simpleappdevelopmentusingtdd.data.repository

import com.example.simpleappdevelopmentusingtdd.data.source.PlayListService
import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playListService: PlayListService
) : PlaylistRepository {
    override suspend fun getPlaylists() =
        playListService.getPlaylists()
}