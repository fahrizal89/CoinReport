package com.fahrizal.coinreport.data.coin.repository

import com.fahrizal.coinreport.data.coin.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun getCoinPrices(refresh: Boolean): Flow<List<Coin>>
}