package com.mpowloka.girlznotifications

import android.os.Bundle
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

            WorkManager.getInstance().enqueue(
                    PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.SECONDS)
                            .build()
            )
        }

    }

    companion object {
        private const val FLUTTER_NOTIFICATIONS_CHANNEL = "notifications"
    }

}
