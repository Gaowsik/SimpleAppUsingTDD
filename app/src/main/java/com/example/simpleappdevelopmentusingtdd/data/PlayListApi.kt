package com.example.simpleappdevelopmentusingtdd.data

import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import retrofit2.http.GET

interface PlayListApi {

    @GET("playlists")
    suspend fun getPlaylists(): List<Playlist>
}