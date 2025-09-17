package com.example.simpleappdevelopmentusingtdd.presentation.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleappdevelopmentusingtdd.databinding.FragmentPlaylistsBinding
import com.example.simpleappdevelopmentusingtdd.presentation.Playlist
import com.example.simpleappdevelopmentusingtdd.presentation.collectLatestLifeCycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaylistsFragment : Fragment() {

    private val viewModel: PlaylistViewmodel by viewModels()
    private lateinit var binding: FragmentPlaylistsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlaylistsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setUpIncomingRecyclerView()
        setUpObservers()
    }

    private fun setUpObservers() {
        this.collectLatestLifeCycleFlow(viewModel.getAllPlaylist) { playList ->
            if (playList != null) {
                if (playList.getOrNull() != null) {
                    setDataPlaylistAdapter(playList.getOrNull()!!)
                }
            }
        }
    }

    private fun setDataPlaylistAdapter(listOfPlaylist: List<Playlist>) {
        binding.recycleListPlaylist.adapter?.let {
            (it as PlayListAdapter).setData(listOfPlaylist)
        }
    }


    private fun setUpIncomingRecyclerView() {
        val adapter = PlayListAdapter() {
        }
        binding.recycleListPlaylist.layoutManager = LinearLayoutManager(context)
        binding.recycleListPlaylist.adapter = adapter
    }


}