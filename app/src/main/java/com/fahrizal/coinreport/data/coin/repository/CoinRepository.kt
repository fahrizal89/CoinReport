package com.fahrizal.coinreport.data.coin.repository

import com.fahrizal.coinreport.domain.model.CoinChart
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoinPrices(refresh: Boolean): Flow<List<CoinChart>>
}