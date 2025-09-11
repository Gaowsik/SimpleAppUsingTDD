package com.example.simpleappdevelopmentusingtdd.data.util

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Utill {
    fun createAppApiClient(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit = retrofitClient(
        baseUrl, okHttpClient
    )

    private fun retrofitClient(
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}