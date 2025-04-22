package com.example.m7_geeco_in.inicisessio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.m7_geeco_in.menu.Header
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.fragments.RetornTop
import com.example.m7_geeco_in.screen.MenuAndroid

import com.example.m7_geeco_in.viewModel.iniciSessioViewModel
import androidx.activity.viewModels
import androidx.lifecycle.Observer

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
        val user = findViewById<EditText>(R.id.usernameEditText)
        val password = findViewById<EditText>(R.id.passwordEditText)
        val viewModel: iniciSessioViewModel by viewModels()

        viewModel.isLoggedIn.observe(this, Observer { success ->
            if (success) {
                val intent = Intent(this@IniciSessio, Header::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credencials incorrectes", Toast.LENGTH_SHORT).show()
            }
        })
        button.setOnClickListener {
            viewModel.iniciarSessio(user.text.toString(), password.text.toString())
        }
    }
}