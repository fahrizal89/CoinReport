package com.fahrizal.coinreport

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.fahrizal.coinreport.ui.worker.CoinPriceWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initScheduledWorker()
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(workerFactory)
            .build()

    private fun initLogger() = Timber.plant(Timber.DebugTree())

    private fun initScheduledWorker() {
        val fetchCoinPricesRequest =
            PeriodicWorkRequestBuilder<CoinPriceWorker>(60, TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                CoinPriceWorker.WORKER_ID,
                ExistingPeriodicWorkPolicy.KEEP,
                fetchCoinPricesRequest
            )
    }
}