package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AfegirIngres : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_afegir_ingres)

        val titol: EditText = findViewById(R.id.et_titol)
        val descripion: EditText = findViewById(R.id.et_descripion)
        val cuantitat: EditText = findViewById(R.id.et_cuantitat)
        val data: EditText = findViewById(R.id.et_data)
        val b1 = findViewById<Button>(R.id.b1)

        // Initialize Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://54.173.54.56/incomes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        b1.setOnClickListener {
            val title = titol.text.toString()
            val description = descripion.text.toString()
            val amount = cuantitat.text.toString().toIntOrNull() // Ensure it can be parsed
            val date = data.text.toString()

            // Check for missing fields
            if (title.isBlank() || description.isBlank() || amount == null || date.isBlank()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Make API call
            apiService.createUsuari(title, description, amount, date).enqueue(object : Callback<Ingressos> {
                override fun onResponse(call: Call<Ingressos>, response: Response<Ingressos>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AfegirIngres, "Income created successfully!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@AfegirIngres, LlistaIngressos::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@AfegirIngres, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Ingressos>, t: Throwable) {
                    Toast.makeText(this@AfegirIngres, "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
