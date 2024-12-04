package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Preferencies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preferencies)
        val b1 = findViewById<Button>(R.id.button)
        b1.setOnClickListener{
            val intent = Intent(this@Preferencies, LlistaIngressos::class.java)
            startActivity(intent)
        }
    }
}