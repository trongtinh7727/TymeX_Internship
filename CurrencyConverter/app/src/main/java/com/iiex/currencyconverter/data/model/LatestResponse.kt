package com.iiex.currencyconverter.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LatestResponse(
    val data: Map<String, Double>
)
