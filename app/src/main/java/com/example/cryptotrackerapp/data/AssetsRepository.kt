package com.example.cryptotrackerapp.data

import com.example.cryptotrackerapp.model.AssetsResponse
import com.example.cryptotrackerapp.service.RetrofitInstance

class AssetsRepository {
    private val assetsService = RetrofitInstance.assetsService

    suspend fun getAssets(): AssetsResponse {
        return assetsService.getAssets()
    }
}