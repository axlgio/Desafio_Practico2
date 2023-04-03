package com.example.desafio_practico

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class splashScreen : AppCompatActivity() {
    private lateinit var handler: Handler

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler.createAsync(Looper.getMainLooper())

        handler.postDelayed({
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)


    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

}