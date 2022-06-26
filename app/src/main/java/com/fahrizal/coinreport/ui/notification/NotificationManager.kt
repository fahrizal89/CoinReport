package com.fahrizal.coinreport.ui.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.fahrizal.coinreport.R
import com.fahrizal.coinreport.ui.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class NotificationManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "CoinReport"
            val descriptionText = "CoinReport App"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManagerManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManagerManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    fun notify(title: String, body: String, notificationId: Int = 1000) {
        Timber.d("Worker notify -> title: ".plus(title).plus(" body: ").plus(body))
        val notifyIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
//        notifyIntent.putExtra(NOTIFICATION_EXTRA, true)
//        notifyIntent.putExtra(NOTIFICATION_ID, notificationId)
        val notifyPendingIntent = getActivity(context, 0, notifyIntent, FLAG_IMMUTABLE)

        val builder = NotificationCompat
            .Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(notifyPendingIntent)
            .setAutoCancel(true)

        NotificationManagerCompat.from(context).notify(notificationId, builder.build())
    }

    private companion object {

        const val CHANNEL_ID = "KELOLA_GAJI"
    }
}