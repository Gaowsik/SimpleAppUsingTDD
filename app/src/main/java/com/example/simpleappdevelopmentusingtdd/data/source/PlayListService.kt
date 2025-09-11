package com.example.simpleappdevelopmentusingtdd.data.source

import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PlayListService {

    @GET("/rest/issuetoken")
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>>
}