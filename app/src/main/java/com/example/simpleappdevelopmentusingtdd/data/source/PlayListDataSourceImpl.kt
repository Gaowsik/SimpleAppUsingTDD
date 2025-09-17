package com.example.simpleappdevelopmentusingtdd.data.source

import com.example.simpleappdevelopmentusingtdd.data.PlayListApi
import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListDataSourceImpl @Inject constructor(
    private val playListApi: PlayListApi
) : PlayListDataSource {
    override suspend fun getPlaylists(): Flow<Result<List<Playlist>>> = flow {
        emit(runCatching { playListApi.getPlaylists() })
    }
}