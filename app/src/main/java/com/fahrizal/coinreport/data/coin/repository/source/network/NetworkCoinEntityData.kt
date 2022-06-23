package com.fahrizal.coinreport.data.coin.repository.source.network

import com.fahrizal.coinreport.data.coin.api.CoinApi
import com.fahrizal.coinreport.data.coin.repository.source.CoinEntityData
import javax.inject.Inject

class NetworkCoinEntityData @Inject constructor(
    private val coinApi: CoinApi
) : CoinEntityData {

    override fun getCoinPrices() {

    }
}