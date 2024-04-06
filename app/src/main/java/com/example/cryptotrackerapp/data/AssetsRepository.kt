package com.example.cryptotrackerapp.data

import com.example.cryptotrackerapp.model.AssetsResponse
import com.example.cryptotrackerapp.service.AssetsService
import com.example.cryptotrackerapp.service.RetrofitInstance
import javax.inject.Inject

class AssetsRepository @Inject constructor(private val assetsService: AssetsService) {
    suspend fun getAssets(): AssetsResponse {
        return assetsService.getAssets()
    }
}