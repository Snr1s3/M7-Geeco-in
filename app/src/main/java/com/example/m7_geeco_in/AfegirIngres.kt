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
import com.example.m7_geeco_in.data.IngresRequest
import kotlinx.coroutines.launch


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
                Toast.makeText(this, "Si us plau, ompli tots els camps correctament.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            b1.isEnabled = false

            lifecycleScope.launch {
                try {
                    val api = geecoinAPI.API()
                    val request = IngresRequest(title, description, amount, date)
                    val response = api.postIngres(request)

                    if (response != null) {
                        Toast.makeText(this@AfegirIngres, "Ingrés creat correctament.", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@AfegirIngres, LlistaIngressos::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@AfegirIngres, "Error en la resposta del servidor.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@AfegirIngres, "Error de connexió: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                } finally {
                    b1.isEnabled = true  // Rehabilitar el botó en qualsevol cas
                }
            }
        }

    }
}

