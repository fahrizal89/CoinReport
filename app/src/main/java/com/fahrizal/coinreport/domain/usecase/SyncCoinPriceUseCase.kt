package com.fahrizal.coinreport.domain.usecase

import com.fahrizal.coinreport.data.coin.repository.CoinRepository
import com.fahrizal.coinreport.data.map.model.Location
import com.fahrizal.coinreport.domain.mapper.CoinPriceMapper.toCoins
import com.fahrizal.coinreport.util.DateUtil
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SyncCoinPriceUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    @FlowPreview
    operator fun invoke(location: Location? = null): Flow<Boolean> {
        return coinRepository.deleteCoinPricesLowerThenTime(DateUtil.getBeginingDay())
            .flatMapConcat { coinRepository.fetchCoinPrices(true) }
            .map { coinResponse -> coinResponse.toCoins(location) }
            .flatMapConcat(coinRepository::saveCoinPrices)
    }
}