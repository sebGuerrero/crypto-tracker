package com.example.cryptotrackerapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val BASE_URL = "https://4ff399d1-53e9-4a28-bc99-b7735bad26bd.mock.pstmn.io/v2/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val assetsService: AssetsService by lazy {
        retrofit.create(AssetsService::class.java)
    }
}