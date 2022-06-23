package com.fahrizal.coinreport.data.coin.model

data class CoinPriceResponse(
    val chartName: String? = null,
    val bpi: Bpi? = null,
    val time: Time? = null,
    val disclaimer: String? = null
) {

    data class Time(
        val updateduk: String? = null,
        val updatedISO: String? = null,
        val updated: String? = null
    )

    data class Bpi(
        val eUR: EUR? = null,
        val gBP: GBP? = null,
        val uSD: USD? = null
    )

    class EUR : CoinBpi()

    class USD : CoinBpi()

    class GBP : CoinBpi()

    abstract class CoinBpi {

        val symbol: String? = null
        val rateFloat: Double? = null
        val code: String? = null
        val rate: String? = null
        val description: String? = null
    }
}

