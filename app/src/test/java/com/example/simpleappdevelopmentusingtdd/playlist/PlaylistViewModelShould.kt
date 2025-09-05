package com.example.simpleappdevelopmentusingtdd.playlist

import com.example.simpleappdevelopmentusingtdd.utills.BaseUnitTest
import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import com.example.simpleappdevelopmentusingtdd.presentation.playlist.PlaylistViewmodel
import com.example.simpleappdevelopmentusingtdd.presentation.repository.PlaylistRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlaylistViewModelShould : BaseUnitTest() {

    val repository = mock<PlaylistRepository>()
    private val playLists = mock<List<Playlist>>()
    private val expected = Result.success(playLists)
    private val exception = RuntimeException("Something went wrong")


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPlaylistsRepository() = runTest {
        val viewmodel = mockSuccessfulCase()
        advanceUntilIdle()
        viewmodel.getAllPlaylist.first()
        verify(repository, times(1)).getPlaylists()

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun emitPlaylistFromRepository() = runTest {
        val viewmodel = mockSuccessfulCase()
        val value = viewmodel.getAllPlaylist.filterNotNull().first()
        assertEquals(expected, value)
    }


    @Test
    fun emitErrorWhenReceiveError() = runTest{
        whenever(repository.getPlaylists()).thenReturn(
            flow {
                emit(Result.failure(exception = exception))
            }
        )

        val viewmodel = PlaylistViewmodel(repository)
        val value = viewmodel.getAllPlaylist.filterNotNull().first()
        assertEquals(exception, value.exceptionOrNull())
    }

    suspend fun mockSuccessfulCase(): PlaylistViewmodel {
        whenever(repository.getPlaylists()).thenReturn(
            flow {
                emit(expected)
            }
        )
        return PlaylistViewmodel(repository)
    }
}