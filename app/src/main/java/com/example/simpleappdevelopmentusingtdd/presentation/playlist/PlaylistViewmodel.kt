package com.example.simpleappdevelopmentusingtdd.presentation.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import com.example.simpleappdevelopmentusingtdd.presentation.repository.PlaylistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewmodel @Inject constructor(
    private val playListRepository: PlaylistRepository
) : ViewModel() {
    private val _getAllPlaylists  = MutableStateFlow<Result<List<Playlist>>?>(null)
    val getAllPlaylist = _getAllPlaylists.asStateFlow()


    init {
        getPlaylists()
    }
    private fun getPlaylists() {
        viewModelScope.launch {
            playListRepository.getPlaylists().collect{
                _getAllPlaylists.value = it
            }
        }

    }
}