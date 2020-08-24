package com.sample.foregroundserviceexample

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.os.Build

// Setting notification channel
const val CHANNEL_ID = "exampleChannel"

class App: Application() {
    /**
     * This will be called before any activity starts.
     * It will start with our App.
     */
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Checking if our SDK is greater than Oreo
        // Notification Channel class is not available in lower versions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_DEFAULT // For a foreground service the notification importance should be at least IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}