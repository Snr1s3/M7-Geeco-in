package com.example.m7_geeco_in.inicisessio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.m7_geeco_in.menu.Header
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.fragments.RetornTop
import com.example.m7_geeco_in.screen.MenuAndroid

class IniciSessio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MenuAndroid(window).hideSystemBar()
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