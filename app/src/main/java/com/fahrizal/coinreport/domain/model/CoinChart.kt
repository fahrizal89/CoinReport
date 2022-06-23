package com.fahrizal.coinreport.domain.model

data class CoinChart(
    val code: String? = "",
    val symbol: String? = "",
    val rate: Double? = 0.0,
    val description: String? = "",
    val rateFloat: Double? = 0.0,
    val chartName: String? = ""
)