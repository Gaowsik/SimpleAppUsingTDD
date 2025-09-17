package com.example.simpleappdevelopmentusingtdd.playlist

import com.example.simpleappdevelopmentusingtdd.data.PlayListApi
import com.example.simpleappdevelopmentusingtdd.data.source.PlayListDataSourceImpl
import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import com.example.simpleappdevelopmentusingtdd.utills.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.whenever

class PlaylistDataSourceShould : BaseUnitTest() {

    private val playlistAPI = mock<PlayListApi>()
    val dataSource = PlayListDataSourceImpl(playlistAPI)
    private val playLists = mock<List<Playlist>>()
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun getPlaylistFromAPI() = runTest {
        dataSource.getPlaylists().first()
        Mockito.verify(playlistAPI, times(1)).getPlaylists()
    }

    @Test
    fun emitListFromApi() = runTest {
        val dataSource = mockSuccessfullCase()

        assertEquals(playLists, dataSource.getPlaylists().first().getOrNull())
    }


    @Test
    fun propogateError() = runTest {
        val dataSource = mockFailiurCase()
        val result = dataSource.getPlaylists().first()
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }

    suspend fun mockSuccessfullCase(): PlayListDataSourceImpl {
        whenever(playlistAPI.getPlaylists()).thenReturn(
            playLists
        )
        return PlayListDataSourceImpl(playlistAPI)
    }

    suspend fun mockFailiurCase(): PlayListDataSourceImpl {
        whenever(playlistAPI.getPlaylists()).thenThrow(exception)
        return PlayListDataSourceImpl(playlistAPI)
    }


}