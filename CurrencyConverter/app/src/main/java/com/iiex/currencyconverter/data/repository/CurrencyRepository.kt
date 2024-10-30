package com.iiex.currencyconverter.data.repository

import com.iiex.currencyconverter.BuildConfig
import com.iiex.currencyconverter.data.model.CurrenciesResponse
import com.iiex.currencyconverter.data.model.LatestResponse
import com.iiex.currencyconverter.data.remote.CurrencyApiService
import javax.inject.Inject


class CurrencyRepository @Inject constructor(
    private val api: CurrencyApiService
) {

    suspend fun getRates(base: String, symbols: String): Result<LatestResponse> {
        val response = api.getLatestRates(BuildConfig.EXCHANGE_API_KEY, base, symbols)
        return if (response.isSuccessful) {
            response.body()?.let { Result.success(it) } ?: Result.failure(Exception("Empty Response"))
        } else {
            Result.failure(Exception("Failed to fetch rates: ${response.message()}"))
        }
    }

    suspend fun getCurrencies(): Result<CurrenciesResponse> {
        return try {
            val response = api.getCurrencies(BuildConfig.EXCHANGE_API_KEY)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Empty Response"))
            } else {
                Result.failure(Exception("Failed to fetch currencies: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
