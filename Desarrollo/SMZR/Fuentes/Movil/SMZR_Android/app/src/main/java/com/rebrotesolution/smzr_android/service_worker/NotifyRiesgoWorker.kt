package com.rebrotesolution.smzr_android.service_worker

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.widget.RemoteViews
import androidx.work.CoroutineWorker

import androidx.work.WorkerParameters
import com.rebrotesolution.smzr_android.LoginActivity
import com.rebrotesolution.smzr_android.R


class NotifyRiesgoWorker(
    var context: Context,
    var params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        generatePushNotification()
        return Result.success()
    }
    
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var  builder: Notification.Builder
    private val channelId = "com.rebrotesolution.smzr_android.service_worker"
    private val description = "Notificacion de alerta de zona de riesgo"

    private fun generatePushNotification() {

        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(context,LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val contentView = RemoteViews(context.packageName, R.layout.notification_layout)
        contentView.setTextViewText(R.id.notify_title,"SMZR")
        contentView.setTextViewText(R.id.notify_content,"¡CUIDADO! Estás acercandote a una zona de riesgo, toma tus precauciones.")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(context,channelId)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.drawable.ic_launcher))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
        }else{
            builder = Notification.Builder(context)
                .setContent(contentView)
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources,R.drawable.ic_launcher))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
        }

        notificationManager.notify(100,builder.build())
    }
}