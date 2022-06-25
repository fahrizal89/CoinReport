package com.fahrizal.coinreport.ui.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.fahrizal.coinreport.dispatcher.CoroutineDispatcherProvider
import com.fahrizal.coinreport.domain.usecase.DeleteCoinPricePreviousDayUseCase
import com.fahrizal.coinreport.domain.usecase.SyncCoinPriceUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class CoinPriceWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val syncCoinPriceUseCase: SyncCoinPriceUseCase,
    private val deleteCoinPricePreviousDayUseCase: DeleteCoinPricePreviousDayUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        withContext(coroutineDispatcherProvider.io) {
            deleteCoinPricePreviousDayUseCase()
                .map { syncCoinPriceUseCase() }
                .collect {
                    Timber.d("CoinPriceWorker Fetch Success")
                }
        }

        return Result.success()
    }

    companion object {

        const val WORKER_ID = "CoinPriceWorker"
    }
}