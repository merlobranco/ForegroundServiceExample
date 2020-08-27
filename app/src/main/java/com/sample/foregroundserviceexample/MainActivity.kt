package com.sample.foregroundserviceexample

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextInput = findViewById(R.id.edit_text_input)
    }

    fun startService(view: View) {
        val input = editTextInput.text.toString()
        val serviceIntent = Intent(this, ExampleService::class.java)
        serviceIntent.putExtra(INPUT_EXTRA_KEY, input)

        // Start the Service while the App is still open
//        startService(serviceIntent)

        /**
         * Start the Service while the App is on the background
         * This method tells the system we want to promote our service to a foreground service as quickly as possible.
         * After calling it we have a window of 5 seconds for calling startForeground from our Service class.
         * If we don't do it in those 5 seconds, the system will kill our service immediately.
         * If we call startService() from the background, it will throw an illegal state exception and our app will crash.
         * There are some exceptions our app is on a temporary white-list,
         * and it will be able to call startService() from the background without crash
         *
         * ContextCompat checks for us if our SDK is equal or bigger than Oreo,
         * required since startForegroundService only works from that SDK version
         */
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    fun stopService(view: View) {
        val serviceIntent = Intent(this, ExampleService::class.java)
        stopService(serviceIntent)
    }
}
