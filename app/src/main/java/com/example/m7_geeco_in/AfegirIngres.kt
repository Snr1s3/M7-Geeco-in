package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

            lifecycleScope.launch {
                try {
                    geecoinAPI.API().postIngres(title, description, amount, date).enqueue(object : Callback<Ingressos> {
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
                } catch (e: Exception) {
                    Toast.makeText(this@AfegirIngres, "Error loading data", Toast.LENGTH_SHORT)
                        .show()
                    Toast.makeText(this@AfegirIngres, "Error: ${e.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
