package com.example.m7_geeco_in.inicisessio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.m7_geeco_in.menu.Header
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.screen.MenuAndroid
import com.example.m7_geeco_in.viewModel.registreViewModel

class activity_registre : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registre)
        MenuAndroid(window).hideSystemBar()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button = findViewById<Button>(R.id.loginButton)

        val viewModel: registreViewModel by viewModels()

        val mail = findViewById<EditText>(R.id.email)
        val nom = findViewById<EditText>(R.id.nom)
        val password = findViewById<EditText>(R.id.password1)
        val password2 = findViewById<EditText>(R.id.password2)
        viewModel.isRegistered.observe(this, Observer { success ->
            if (success) {
                val intent = Intent(this@activity_registre, Login::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Les credencials son incorrectes", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.errorNomUsuari.observe(this) { error ->
            error?.let {
                nom.error = it
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        button.setOnClickListener {
            viewModel.registre(nom.text.toString(), mail.text.toString(), password.text.toString(), password2.text.toString(), false)
        }
    }
}