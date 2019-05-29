package com.mpowloka.girlznotifications

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.util.*

class NotificationWorker(
        context: Context, workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {

        val channelId = inputData.getString(TARGET_CHANNEL_ID_KEY) ?: return Result.failure()

        val notification = NotificationCompat.Builder(applicationContext, channelId)
                .setContentTitle(NOTIFICATION_CONTENT_TITLE)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText(
                        AVAILABLE_CONTENT_TEXTS[Random().nextInt(AVAILABLE_CONTENT_TEXTS.size)]
                )
                .build()

        NotificationManagerCompat.from(applicationContext).notify(
                0, notification
        )

        return Result.success()
    }

    companion object {

        private const val NOTIFICATION_CONTENT_TITLE = "Girls..."

        private val AVAILABLE_CONTENT_TEXTS = listOf(
                "Co?",
                "Shcho shcho?",
                "What?",
                "Kife? (I cannot find how to spell that, lol)"
        )

        const val TARGET_CHANNEL_ID_KEY = "TARGET_CHANNEL_ID_KEY"

    }
}