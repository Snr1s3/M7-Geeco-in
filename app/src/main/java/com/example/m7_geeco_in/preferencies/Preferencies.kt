package com.example.m7_geeco_in.preferencies

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.ingres.LlistaIngressos
import com.example.m7_geeco_in.screen.MenuAndroid

class Preferencies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MenuAndroid(window).hideSystemBar()
        setContentView(R.layout.activity_preferencies)
        val b1 = findViewById<Button>(R.id.button)
        b1.setOnClickListener{
            val intent = Intent(this@Preferencies, LlistaIngressos::class.java)
            startActivity(intent)
        }
    }
}