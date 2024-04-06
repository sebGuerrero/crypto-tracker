package com.example.cryptotrackerapp.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstance {

    private val BASE_URL = "https://4ff399d1-53e9-4a28-bc99-b7735bad26bd.mock.pstmn.io/v2/"
//    SIN HILT
//    private val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    val assetsService: AssetsService by lazy {
//        retrofit.create(AssetsService::class.java)
//    }

    // Usando Hilt

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAssetsService(retrofit: Retrofit): AssetsService {
        return retrofit.create(AssetsService::class.java)
    }

}