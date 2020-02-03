package com.sanmidev.notification

import android.app.*
import android.app.Notification
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat

const val NOTIFICATION_CHANNEL = " MY custom Notification channel"
const val NOTIFICATION_CHANNEL_ID = "1"
const val NOTIFICATION_ID : Int = 0

class Notification( private val context: Context) {

    lateinit var notificationManager: NotificationManager


     fun createMessageNotificationChannel() {
         notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel: NotificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL,
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Testing Notification"

            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun createNotificationBuilder(): NotificationCompat.Builder {
        val notify : NotificationCompat.Builder  = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID )
            .setContentTitle("Hello world")
            .setContentText("THis is hello world from notification.")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentIntent(createPendingIntent())
            .setAutoCancel(true)
        return notify
    }

    fun createNewNotificationBuilder(): Notification {
        val notify : NotificationCompat.Builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Hello world 2")
            .setContentText("This  is the new hello world from notification")
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentIntent(createPendingIntent())
            .setAutoCancel(true)
        return  notify.build()
    }
    fun fireNotification() {
        createMessageNotificationChannel()
        notificationManager.notify(NOTIFICATION_ID, createNotificationBuilder().build())

    }

    fun fireSecondNotification(){
        createMessageNotificationChannel()
        notificationManager.notify(NOTIFICATION_ID, createNewNotificationBuilder())
    }

    fun createPendingIntent(): PendingIntent {
        val notificationIntent = Intent(context, SecondActivity::class.java)

        return TaskStackBuilder.create(context).addNextIntentWithParentStack(notificationIntent).getPendingIntent(
            NOTIFICATION_ID, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    fun cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }
}