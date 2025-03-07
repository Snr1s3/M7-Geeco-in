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
        val descripcio: EditText = findViewById(R.id.et_descripcio)
        val quantitat: EditText = findViewById(R.id.et_quantitat)
        val data: EditText = findViewById(R.id.et_data)
        val b1 = findViewById<Button>(R.id.b1)

        b1.setOnClickListener {
            val title = titol.text.toString().trim()
            val description = descripcio.text.toString().trim()
            val amount = quantitat.text.toString().toIntOrNull()
            val date = data.text.toString().trim()
            if (title.isBlank() || description.isBlank() || amount == null || date.isBlank()) {
                Toast.makeText(this, "Si us plau, ompli tots els camps.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                try {
                    val api = geecoinAPI.API()
                    api.postIngres(title, description, amount, date).enqueue(object : Callback<Ingressos> {
                        override fun onResponse(call: Call<Ingressos>, response: Response<Ingressos>) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@AfegirIngres, "Ingrés creat correctament.", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@AfegirIngres, LlistaIngressos::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@AfegirIngres, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<Ingressos>, t: Throwable) {
                            Toast.makeText(this@AfegirIngres, "Error de connexió: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                } catch (e: Exception) {
                    Toast.makeText(this@AfegirIngres, "Error en carregar les dades: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
