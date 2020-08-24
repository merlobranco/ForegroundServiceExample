package com.sample.foregroundserviceexample

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ExampleService: Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
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