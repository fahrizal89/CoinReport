package com.fahrizal.coinreport.data.coin.mapper

import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import com.fahrizal.coinreport.domain.model.CoinChart

object CoinPriceMapper {

    fun CoinPriceResponse.toCoinCharts(): List<CoinChart> {
        val coinCharts = ArrayList<CoinChart>()

        if (bpi == null) return coinCharts

        bpi.uSD?.let { usd ->
            coinCharts.add(usd.toCoinChart(chartName ?: ""))
        }
        bpi.eUR?.let { eur ->
            coinCharts.add(eur.toCoinChart(chartName ?: ""))
        }
        bpi.gBP?.let { gbp ->
            coinCharts.add(gbp.toCoinChart(chartName ?: ""))
        }

        return coinCharts
    }

    private fun CoinPriceResponse.CoinBpi.toCoinChart(chartName: String): CoinChart {
        return CoinChart(
            code,
            symbol,
            rate?.toDouble(),
            description,
            rateFloat,
            chartName
        )
    }
}