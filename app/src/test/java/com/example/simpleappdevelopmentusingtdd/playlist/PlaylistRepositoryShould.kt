package com.example.simpleappdevelopmentusingtdd.playlist

import com.example.simpleappdevelopmentusingtdd.data.repository.PlaylistRepositoryImpl
import com.example.simpleappdevelopmentusingtdd.data.source.PlayListDataSource
import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import com.example.simpleappdevelopmentusingtdd.utills.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.kotlin.whenever

class PlaylistRepositoryShould : BaseUnitTest() {

    private val service = mock<PlayListDataSource>()

    val repository = PlaylistRepositoryImpl(service)
    private val playLists = mock<List<Playlist>>()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistFromService() = runTest {
        repository.getPlaylists()
        Mockito.verify(service, times(1)).getPlaylists()
    }

    @Test
    fun emitPlaylistFromService() = runTest {
        val repository = mockSuccessfulCase()
        assertEquals(playLists, repository.getPlaylists().first().getOrNull())
    }


    private suspend fun mockSuccessfulCase(): PlaylistRepositoryImpl {
        whenever(service.getPlaylists()).thenReturn(
            flow {
                emit(Result.success(playLists))
            }

        )

        return PlaylistRepositoryImpl(service)
    }

    @Test
    fun propogateErrors() = runTest {
        val repository = mockFailureCase()
        assertEquals(
            "Something went wrong",
            repository.getPlaylists().first().exceptionOrNull()?.message
        )

    }


    private suspend fun mockFailureCase(): PlaylistRepositoryImpl {

        whenever(service.getPlaylists()).thenReturn(

            flow {
                emit(Result.failure(RuntimeException("Something went wrong")))

            }
        )

        return PlaylistRepositoryImpl(service)
    }


}