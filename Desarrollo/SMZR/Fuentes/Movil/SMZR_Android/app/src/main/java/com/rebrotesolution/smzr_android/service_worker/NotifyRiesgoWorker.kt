package com.rebrotesolution.smzr_android.service_worker

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.Operation
import androidx.work.Worker
import androidx.work.WorkerParameters


class NotifyRiesgoWorker(
    var context: Context,
    var params: WorkerParameters
): CoroutineWorker(context,params){

    override suspend fun doWork(): Result {
        Log.i("TAG","LLAMADO AL WORK")
        generatePushNotification()
        return  Result.success()
    }

    private fun generatePushNotification(){
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, 1.toString())
            .setSmallIcon(R.drawable.ic_dialog_alert)
            .setColor(Color.RED)
            .setContentTitle("!CUIDADO!")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Estas acercandote a la zona de riesgo, toma tus precauciones")

            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "Channel_name"
            val description = "description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance)
            channel.description = description
            val notificationManager =
                context.getSystemService(
                    NotificationManager::class.java
                )
            notificationManager.createNotificationChannel(channel)
        }
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(100, mBuilder.build())
    }
}