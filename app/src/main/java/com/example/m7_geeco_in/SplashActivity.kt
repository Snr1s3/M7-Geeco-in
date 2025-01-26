package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logoApp)
        val rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotacio)
        logo.startAnimation(rotateAnimation)

        // Configurar un temporizador de 2 segundos
        Handler().postDelayed({
            // Iniciar MainActivity después de 2 segundos
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 2000 milisegundos = 2 segundos
    }
}
