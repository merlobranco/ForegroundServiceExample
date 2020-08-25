package com.sample.foregroundserviceexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

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

        startService(serviceIntent)

    }

    fun stopService(view: View) {
        val serviceIntent = Intent(this, ExampleService::class.java)
        stopService(serviceIntent)
    }
}
