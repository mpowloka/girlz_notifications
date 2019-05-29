package com.mpowloka.girlznotifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.work.*
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
        MethodChannel(flutterView, FLUTTER_NOTIFICATIONS_CHANNEL).setMethodCallHandler { call, _ ->

            when (call.method) {

                "startReminders" -> {

                    WorkManager.getInstance().cancelAllWork()

                    val inputData = workDataOf(NotificationWorker.TARGET_CHANNEL_ID_KEY to CHANNEL_ID)

                    WorkManager.getInstance().enqueueUniqueWork(
                            SCHEDULE_NOTIFICATIONS_WORKER_NAME,
                            ExistingWorkPolicy.REPLACE,
                            OneTimeWorkRequestBuilder<ScheduleNotificationsWorker>()
                                    .setInitialDelay(12, TimeUnit.HOURS)
                                    .setInputData(inputData)
                                    .build()
                    )

                    Toast.makeText(this, "Something will continue to happen. Later.", Toast.LENGTH_SHORT).show()
                }

                "stopReminders" -> {
                    WorkManager.getInstance().cancelAllWork()

                    Toast.makeText(this, "Mission aborted.", Toast.LENGTH_SHORT).show()
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

        const val NOTIFICATIONS_WORKER_NAME = "NOTIFICATIONS_WORKER_NAME"



        private const val CHANNEL_ID = "42"

        private const val SCHEDULE_NOTIFICATIONS_WORKER_NAME = "SCHEDULE_NOTIFICATIONS_WORKER_NAME"

        private const val FLUTTER_NOTIFICATIONS_CHANNEL = "notifications"

        private const val ANDROID_NOTIFICATIONS_CHANNEL_NAME = "Gentle reminders"
    }

}
