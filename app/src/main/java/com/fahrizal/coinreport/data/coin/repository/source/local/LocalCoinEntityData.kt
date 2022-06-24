package com.fahrizal.coinreport.data.coin.repository.source.local

import com.fahrizal.coinreport.data.coin.dao.CoinDao
import com.fahrizal.coinreport.data.coin.model.Coin
import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import com.fahrizal.coinreport.data.coin.repository.source.CoinEntityData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalCoinEntityData @Inject constructor(
    private val coinDao: CoinDao
) : CoinEntityData {

    override fun getCoinPrices(): Flow<CoinPriceResponse> {
        return TODO()
    }

    fun saveCoinPrices(coins: List<Coin>): Flow<Boolean> {
        return flow {
            coinDao.insert(coins)
            emit(true)
        }
    }
}