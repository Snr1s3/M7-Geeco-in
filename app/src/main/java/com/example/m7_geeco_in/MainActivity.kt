package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment_top())
                .commit()
        }
        val button = findViewById<Button>(R.id.isBoto)
        button.setOnClickListener {
            val intent = Intent(this@MainActivity, IniciSessio::class.java)
            startActivity(intent)
        }
        val txt= findViewById<TextView>(R.id.cCT)
        txt.setOnClickListener {
            val intent = Intent(this@MainActivity, activity_registre::class.java)
            startActivity(intent)
        }
    }
}
