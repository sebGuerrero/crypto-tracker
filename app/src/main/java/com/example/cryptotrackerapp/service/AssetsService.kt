package com.example.cryptotrackerapp.service

import com.example.cryptotrackerapp.model.AssetsResponse
import retrofit2.http.GET

interface AssetsService {
    @GET("assets")
    suspend fun getAssets(): AssetsResponse
}