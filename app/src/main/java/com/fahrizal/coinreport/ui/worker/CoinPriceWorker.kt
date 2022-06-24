package com.fahrizal.coinreport.ui.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.fahrizal.coinreport.dispatcher.CoroutineDispatcherProvider
import com.fahrizal.coinreport.domain.usecase.GetCoinPriceUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
class CoinPriceWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val getCoinPriceUseCase: GetCoinPriceUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        withContext(coroutineDispatcherProvider.io) {
            getCoinPriceUseCase.invoke(true).collect {
                Timber.d("CoinPriceWorker Fetch Success")
            }
        }

        return Result.success()
    }

    companion object {

        const val WORKER_ID = "CoinPriceWorker"
    }
}