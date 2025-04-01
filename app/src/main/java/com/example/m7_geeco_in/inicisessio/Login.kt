package com.example.m7_geeco_in.inicisessio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.fragments.fragment_top
import com.example.m7_geeco_in.screen.MenuAndroid

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        MenuAndroid(window).hideSystemBar()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment_top())
                .commit()
        }
        val button = findViewById<Button>(R.id.isBoto)
        button.setOnClickListener {
            val intent = Intent(this@Login, IniciSessio::class.java)
            startActivity(intent)
        }
        val b2 = findViewById<Button>(R.id.but2)
        b2.setOnClickListener {
            val intent = Intent(this@Login, Ajuda::class.java)
            startActivity(intent)
        }
        val txt= findViewById<TextView>(R.id.cCT)
        txt.setOnClickListener {
            val intent = Intent(this@Login, activity_registre::class.java)
            startActivity(intent)
        }


    }
}
