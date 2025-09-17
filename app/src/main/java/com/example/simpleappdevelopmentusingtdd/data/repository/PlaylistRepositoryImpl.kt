package com.example.simpleappdevelopmentusingtdd.data.repository

import com.example.simpleappdevelopmentusingtdd.data.source.PlayListDataSource
import javax.inject.Inject

class PlaylistRepositoryImpl @Inject constructor(
    private val playListDataSource: PlayListDataSource
) : PlaylistRepository {
    override suspend fun getPlaylists() =
        playListDataSource.getPlaylists()
}