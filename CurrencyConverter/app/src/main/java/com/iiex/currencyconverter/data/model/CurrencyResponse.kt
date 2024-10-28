package com.iiex.currencyconverter.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
