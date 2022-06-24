package com.fahrizal.coinreport.data.coin.model

data class Coin(
    val code: String? = "",
    val symbol: String? = "",
    val rate: Double? = 0.0,
    val description: String? = "",
    val rateFloat: Double? = 0.0,
    val chartName: String? = ""
)