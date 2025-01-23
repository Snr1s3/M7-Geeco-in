package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AfegirDespesa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_afegir_despesa)
        val b1 = findViewById<Button>(R.id.b1)
        b1.setOnClickListener{
            val intent = Intent(this@AfegirDespesa, LlistaDespeses::class.java)
            startActivity(intent)
        }
    }
}