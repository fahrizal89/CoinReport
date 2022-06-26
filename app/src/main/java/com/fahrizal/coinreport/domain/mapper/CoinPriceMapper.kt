package com.fahrizal.coinreport.domain.mapper

import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import com.fahrizal.coinreport.data.map.model.Location
import java.util.*

object CoinPriceMapper {

    fun CoinPriceResponse.toCoins(location: Location? = null): List<Coin> {
        val coins = ArrayList<Coin>()

        if (bpi == null) return coins

        bpi.usd?.let { usd ->
            coins.add(usd.toCoins(chartName ?: "", location))
        }
        bpi.eur?.let { eur ->
            coins.add(eur.toCoins(chartName ?: "", location))
        }
        bpi.gbp?.let { gbp ->
            coins.add(gbp.toCoins(chartName ?: "", location))
        }

        return coins
    }

    private fun CoinPriceResponse.CoinBpi.toCoins(
        chartName: String,
        location: Location? = null
    ): Coin {
        return Coin(
            null,
            code,
            symbol,
            rateFloat,
            description,
            chartName,
            Date().time,
            location?.latitude ?: 0.0,
            location?.longitude ?: 0.0
        )
    }
}