package com.fahrizal.coinreport.data.coin.repository

import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun fetchCoinPrices(refresh: Boolean): Flow<CoinPriceResponse>
    fun saveCoinPrices(coins: List<Coin>): Flow<Boolean>
    fun deleteCoinPricesLowerThenTime(time: Long): Flow<Boolean>
}