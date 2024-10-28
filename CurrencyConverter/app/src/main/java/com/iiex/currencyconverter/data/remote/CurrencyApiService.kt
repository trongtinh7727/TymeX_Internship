package com.iiex.currencyconverter.data.remote

import com.iiex.currencyconverter.data.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {

    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key") accessKey: String,
        @Query("symbols") symbols: String
    ): Response<CurrencyResponse>
}