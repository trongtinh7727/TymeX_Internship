package com.iiex.currencyconverter.data.remote

import com.iiex.currencyconverter.data.model.CurrenciesResponse
import com.iiex.currencyconverter.data.model.LatestResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("latest")
    suspend fun getLatestRates(
        @Query("apikey") accessKey: String,
        @Query("base_currency") base: String,
        @Query("currencies") symbols: String
    ): Response<LatestResponse>

    @GET("currencies")
    suspend fun getCurrencies(
        @Query("apikey") apiKey: String
    ): Response<CurrenciesResponse>
}