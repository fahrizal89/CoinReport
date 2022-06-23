package com.fahrizal.coinreport.data.coin.repository.source.local

import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import com.fahrizal.coinreport.data.coin.repository.source.CoinEntityData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCoinEntityData @Inject constructor() : CoinEntityData {

    override fun getCoinPrices(): Flow<CoinPriceResponse> {
        return TODO()
    }
}