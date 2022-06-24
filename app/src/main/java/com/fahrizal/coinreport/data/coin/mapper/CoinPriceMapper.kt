package com.fahrizal.coinreport.data.coin.mapper

import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import com.fahrizal.coinreport.data.coin.model.Coin
import java.util.*
import kotlin.collections.ArrayList

object CoinPriceMapper {

    fun CoinPriceResponse.toCoins(): List<Coin> {
        val coins = ArrayList<Coin>()

        if (bpi == null) return coins

        bpi.uSD?.let { usd ->
            coins.add(usd.toCoins(chartName ?: ""))
        }
        bpi.eUR?.let { eur ->
            coins.add(eur.toCoins(chartName ?: ""))
        }
        bpi.gBP?.let { gbp ->
            coins.add(gbp.toCoins(chartName ?: ""))
        }

        return coins
    }

    private fun CoinPriceResponse.CoinBpi.toCoins(chartName: String): Coin {
        return Coin(
            null,
            code,
            symbol,
            rate?.toDouble(),
            description,
            rateFloat,
            chartName,
            Date().time
        )
    }
}