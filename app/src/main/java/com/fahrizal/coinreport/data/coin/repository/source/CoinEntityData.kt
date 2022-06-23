package com.fahrizal.coinreport.data.coin.repository.source

import com.fahrizal.coinreport.data.coin.model.CoinPriceResponse
import kotlinx.coroutines.flow.Flow

interface CoinEntityData {

    fun getCoinPrices(): Flow<CoinPriceResponse>
}