package com.iiex.currencyconverter.data.repository

import com.iiex.currencyconverter.BuildConfig
import com.iiex.currencyconverter.data.model.CurrencyResponse
import com.iiex.currencyconverter.data.remote.CurrencyApiService
import javax.inject.Inject


class CurrencyRepository @Inject constructor(
    private val api: CurrencyApiService
) {
    suspend fun getRates(symbols: String): Result<CurrencyResponse> {
        val response = api.getLatestRates(BuildConfig.EXCHANGE_API_KEY,symbols)
        return if (response.isSuccessful) {
            response.body()?.let { Result.success(it) } ?: Result.failure(Exception("Empty Response"))
        } else {
            Result.failure(Exception(response.message()))
        }
    }
}
