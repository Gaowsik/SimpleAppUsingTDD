package com.example.simpleappdevelopmentusingtdd.presentation

import com.example.simpleappdevelopmentusingtdd.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.mipmap.ic_launcher
)
