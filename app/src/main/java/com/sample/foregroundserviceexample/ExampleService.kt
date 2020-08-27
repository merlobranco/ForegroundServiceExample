package com.sample.foregroundserviceexample

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat

const val INPUT_EXTRA_KEY = "inputExtra"

class ExampleService: Service() {

    override fun onCreate() {
        super.onCreate()
    }

    /**
     * The work we do in onStartCommand runs on UI thread by default
     * Which means if we do some heavy operations in here without creating a separated working thread (coroutine)
     * and then start our App, the App will not respond
     * There is a Subclass of Service that automatically creates a worker thread called IntentService
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent!!.getStringExtra(INPUT_EXTRA_KEY)

        // Opening the current activity from the triggered notification from the service
        val activityIntent = Intent(this, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)


        var notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Example Service")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_android)
            .setContentIntent(activityPendingIntent)
            .build()

        // Since we are using a service we could call the this method
        // instead of notificationManager.notify(1, notification)
//        startForeground(1, notification)

        /**
         * We will return what will happen when our system kills our service
         *      START_NOT_STICKY: The service will just be gone and not started again
         *      START_STICKY: The system will restart our service asap, but the intent we passed to it will be null
         *      START_REDELIVER_INTENT: The system will restart our service asap, and it will pass the last intent to it again
         *                              This played a bigger role for background services, because a foreground is very unlikely to be kill
         */

        /**
         * In order to stop the service from the inside when is the work is done
         * we could call stopSelf() from inside the Service
         */
        stopSelf()

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    // This method is mandatory and we only need it for bound services
    // Bound services: services where other components could communicate back and forth by binding to it
    // Our service will be called at start service, this method is not required
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}