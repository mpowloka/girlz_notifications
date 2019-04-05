package com.mpowloka.girlznotifications

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

        MethodChannel(flutterView, FLUTTER_NOTIFICATIONS_CHANNEL).setMethodCallHandler { call, result ->

            when (call.method) {

                "startReminders" -> {

                    WorkManager.getInstance().enqueueUniquePeriodicWork(
                            WORKER_NAME,
                            ExistingPeriodicWorkPolicy.REPLACE,
                            PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.SECONDS)
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

    companion object {
        private const val FLUTTER_NOTIFICATIONS_CHANNEL = "notifications"
        private const val WORKER_NAME = "WORKER_NAME"
    }

}
