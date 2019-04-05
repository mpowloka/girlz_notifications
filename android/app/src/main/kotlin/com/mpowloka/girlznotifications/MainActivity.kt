package com.mpowloka.girlznotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import java.util.concurrent.TimeUnit

class MainActivity : FlutterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)

        createNotificationsChannel()

        observeFlutterChannel()

    }

    private fun observeFlutterChannel() {
        MethodChannel(flutterView, FLUTTER_NOTIFICATIONS_CHANNEL).setMethodCallHandler { call, result ->

            when (call.method) {

                "startReminders" -> {

                    WorkManager.getInstance().enqueueUniquePeriodicWork(
                            WORKER_NAME,
                            ExistingPeriodicWorkPolicy.REPLACE,
                            PeriodicWorkRequestBuilder<NotificationWorker>(12, TimeUnit.HOURS)
                                    .build()
                    )

                    println("Worker enqueued!")
                }

                "stopReminders" -> {
                    WorkManager.getInstance().cancelAllWork()

                    println("All workers stopped!")
                }

            }
        }
    }

    private fun createNotificationsChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                    CHANNEL_ID,
                    ANDROID_NOTIFICATIONS_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {

        private const val CHANNEL_ID = "42"

        private const val FLUTTER_NOTIFICATIONS_CHANNEL = "notifications"

        private const val ANDROID_NOTIFICATIONS_CHANNEL_NAME = "Gentle reminders"

        private const val WORKER_NAME = "WORKER_NAME"
    }

}
