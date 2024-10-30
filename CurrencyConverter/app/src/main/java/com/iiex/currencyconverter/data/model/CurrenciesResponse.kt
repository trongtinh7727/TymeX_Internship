package com.iiex.currencyconverter.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrenciesResponse(
    val data: Map<String, CurrenciesData>
)

@Serializable
data class CurrenciesData(
    val symbol: String,
    val name: String,
    @SerialName("symbol_native") val symbolNative: String,
    @SerialName("decimal_digits") val decimalDigits: Int,
    val rounding: Int,
    val code: String,
    @SerialName("name_plural")  val namePlural: String,
    val type: String
)
