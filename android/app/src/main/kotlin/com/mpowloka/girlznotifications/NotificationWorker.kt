package com.mpowloka.girlznotifications

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(
        context: Context, workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val channelId = inputData.getString(TARGET_CHANNEL_ID_KEY) ?: return Result.failure()

        val notification = NotificationCompat.Builder(applicationContext, channelId)
                .setContentTitle("Hey beauty!")
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText("You look stunning today!")
                .build()

        NotificationManagerCompat.from(applicationContext).notify(
                0, notification
        )

        return Result.success()
    }

    companion object {

        const val TARGET_CHANNEL_ID_KEY = "TARGET_CHANNEL_ID_KEY"

    }
}