package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class IniciSessio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inici)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RetornTop())
                .commit()
        }
        val button = findViewById<Button>(R.id.loginButton)
        button.setOnClickListener{
            val intent = Intent(this@IniciSessio, Header::class.java)
            startActivity(intent)
        }
    }
}