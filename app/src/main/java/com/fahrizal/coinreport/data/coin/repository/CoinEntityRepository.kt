package com.fahrizal.coinreport.data.coin.repository

import com.fahrizal.coinreport.data.coin.repository.source.local.LocalCoinEntityData
import com.fahrizal.coinreport.data.coin.repository.source.network.NetworkCoinEntityData
import javax.inject.Inject

class CoinEntityRepository @Inject constructor(
    private val localCoinEntityData: LocalCoinEntityData,
    private val networkCoinEntityData: NetworkCoinEntityData
) : CoinRepository {

    override fun getCoinPrices() {
//        networkCoinEntityData.getCoinPrices()
    }
}