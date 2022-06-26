package com.fahrizal.coinreport.ui.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.fahrizal.coinreport.dispatcher.CoroutineDispatcherProvider
import com.fahrizal.coinreport.domain.usecase.DeleteCoinPricePreviousDayUseCase
import com.fahrizal.coinreport.domain.usecase.GetCurrentLocationUseCase
import com.fahrizal.coinreport.domain.usecase.SyncCoinPriceUseCase
import com.fahrizal.coinreport.ui.notification.NotificationManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class CoinPriceWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val syncCoinPriceUseCase: SyncCoinPriceUseCase,
    private val deleteCoinPricePreviousDayUseCase: DeleteCoinPricePreviousDayUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val notificationManager: NotificationManager
) : CoroutineWorker(appContext, workerParams) {

    @FlowPreview
    override suspend fun doWork(): Result {
        Timber.d("CoinPriceWorker doWork()")
        withContext(coroutineDispatcherProvider.io) {
            deleteCoinPricePreviousDayUseCase()
                .map { syncCoinPriceUseCase() }
                .flatMapConcat { getCurrentLocationUseCase() }
                .collect {
                    val content = "CoinPriceWorker Fetch Success"
                        .plus(it.latitude).plus(",").plus(it.longitude)

                    notificationManager.notify("CoinPrice", content)

                    Result.success()
                }
        }

        return Result.success()
    }

    companion object {

        const val WORKER_ID = "CoinPriceWorker"
    }
}