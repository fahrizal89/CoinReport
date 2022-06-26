package com.fahrizal.coinreport.domain.usecase

import com.fahrizal.coinreport.data.coin.repository.CoinRepository
import com.fahrizal.coinreport.util.DateUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SyncCoinPriceUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return coinRepository.deleteCoinPricesLowerThenTime(DateUtil.getBeginingDay())
            .flatMapConcat { coinRepository.getCoinPrices(true)}
            .flatMapConcat(coinRepository::saveCoinPrices)
    }
}