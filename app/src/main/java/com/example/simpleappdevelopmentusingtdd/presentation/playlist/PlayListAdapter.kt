package com.example.simpleappdevelopmentusingtdd.presentation.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleappdevelopmentusingtdd.R
import com.example.simpleappdevelopmentusingtdd.databinding.ItemPlaylistBinding
import com.example.simpleappdevelopmentusingtdd.presentation.Playlist

class PlayListAdapter(var onItemClicked: (Playlist) -> Unit) :
    RecyclerView.Adapter<PlayListAdapter.PlaylistViewHolder>() {

    private var playlist = listOf<Playlist>()

    fun setData(listOfPlaylist: List<Playlist>) {
        playlist = listOfPlaylist
        notifyDataSetChanged()
    }


    inner class PlaylistViewHolder(private val mBinding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun onBind(playlist: Playlist) {
            bindDataToUi(playlist = playlist)

            mBinding.constraintMain.setOnClickListener {
                this@PlayListAdapter.onItemClicked.invoke(playlist)
            }

        }

        private fun bindDataToUi(playlist: Playlist) {
            mBinding.textPlaylistName.text = playlist.name
            mBinding.textPlayListCategory.text = playlist.category
            mBinding.imageView.setImageResource(R.mipmap.ic_launcher)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflate = ItemPlaylistBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PlaylistViewHolder(inflate)
    }

    override fun getItemCount(): Int = playlist.size


    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) = holder.run {
        val playlistItem = playlist[position]
        onBind(playlist = playlistItem)
    }

}