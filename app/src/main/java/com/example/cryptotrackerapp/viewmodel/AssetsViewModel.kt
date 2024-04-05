package com.example.cryptotrackerapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.cryptotrackerapp.data.AssetsRepository
import com.example.cryptotrackerapp.model.Asset
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class AssetsViewModel: ViewModel() {
    private val assetsRepository = AssetsRepository()

    var assets = mutableStateListOf<Asset>()
    fun fetchAssets() {
        viewModelScope.launch {
            try {
                val response = assetsRepository.getAssets().data
                val assetsResponse = response.map { assetResponse ->
//                    val price = assetResponse.priceUsd.format("%.2f").toDouble()
//                    val percentage = assetResponse.changePercent24Hr.format("%.2f").toDouble()
                    val roundedPrice = String.format("%.2f", assetResponse.priceUsd.toDouble()).toDouble()
                    val percentage = String.format("%.2f", assetResponse.changePercent24Hr.toDouble()).toDouble()

                    Asset(
                        assetResponse.id,
                        assetResponse.name,
                        assetResponse.symbol,
                        roundedPrice,
                        percentage
                    )
                }
                assets.addAll(assetsResponse)
            } catch (e: Exception) {
                Log.e("FetchAssets", e.message.toString())
            }
        }
    }
}