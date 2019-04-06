package com.mpowloka.girlznotifications

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class ScheduleNotificationsWorker(
        context: Context, workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {

        WorkManager.getInstance().enqueueUniquePeriodicWork(
                MainActivity.NOTIFICATIONS_WORKER_NAME,
                ExistingPeriodicWorkPolicy.REPLACE,
                PeriodicWorkRequestBuilder<NotificationWorker>(12, TimeUnit.HOURS)
                        .setInputData(inputData)
                        .build()
        )

        return Result.success()
    }
}