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

        private const val NOTIFICATION_CONTENT_TITLE = "Kasia..."

        private val AVAILABLE_CONTENT_TEXTS = listOf(
                "Wyglądasz doooooobrzeeee!",
                "Lubię Cię!",
                "Paweł nie ma racji!",
                "Miłego dnia!",
                "♫♫♫ Ty jesteś RUDA! ♫♫♫",
                "No cześć, Maleńka...",
                "Robisz coś wieczorem?",
                "Czy sprawdziłaś już dzisiejszy status WorldWarBot 2020?",
                "Cieszę się, że Cię poznałem!",
                "Zjedz dzisiaj ananasa!",
                "Uśmiechnij się!",
                "Masz cudowne oczy!",
                "Uśmiechnij się jak najszerzej!",
                "Nigdy nie miałem lepszej kochanki od Ciebie!",
                "Przygodzie powiedziałem, że poznaliśmy się na Woodstocku",
                "Paulina Pawlak myśli, że spadłaś na mnie z okna"
        )

        const val TARGET_CHANNEL_ID_KEY = "TARGET_CHANNEL_ID_KEY"

    }
}