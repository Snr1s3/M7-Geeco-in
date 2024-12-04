package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Ajuda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ajuda)

        val b1 = findViewById<Button>(R.id.b1)
        b1.setOnClickListener {
            val intent = Intent(this@Ajuda, IniciSessio::class.java)
            startActivity(intent)
        }

        val b2 = findViewById<Button>(R.id.b2)
        b2.setOnClickListener {
            val intent = Intent(this@Ajuda, IniciSessio::class.java)
            startActivity(intent)
        }

        val b3 = findViewById<Button>(R.id.b3)
        b3.setOnClickListener {
            val intent = Intent(this@Ajuda, MainActivity::class.java)
            startActivity(intent)
        }
    }
}