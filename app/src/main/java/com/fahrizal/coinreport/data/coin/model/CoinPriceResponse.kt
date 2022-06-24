package com.fahrizal.coinreport.data.coin.model

import com.google.gson.annotations.SerializedName

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
        @SerializedName("EUR")
        val eur: EUR? = null,
        @SerializedName("GBP")
        val gbp: GBP? = null,
        @SerializedName("USD")
        val usd: USD? = null
    )

    class EUR : CoinBpi()

    class USD : CoinBpi()

    class GBP : CoinBpi()

    abstract class CoinBpi {

        val symbol: String? = null
        @SerializedName("rate_float")
        val rateFloat: Double? = null
        val code: String? = null
        val rate: String? = null
        val description: String? = null
    }
}

