package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AfegirDespesa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_afegir_ingres)

        val titol: EditText = findViewById(R.id.et_titol)
        val descripcio: EditText = findViewById(R.id.et_descripion)
        val quantitat: EditText = findViewById(R.id.et_cuantitat)
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
            /*lifecycleScope.launch {
                try {
                    val api = geecoinAPI.API()
                    api.postDespese(title, description, amount, date).enqueue(object :
                        Callback<Despesses> {
                        override fun onResponse(
                            call: Call<Despesses>,
                            response: Response<Despesses>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@AfegirDespesa, "Despessa creat correctament.", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@AfegirDespesa, LlistaIngressos::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@AfegirDespesa, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<Despesses>, t: Throwable) {
                            Toast.makeText(this@AfegirDespesa, "Error de connexió: ${t.message}", Toast.LENGTH_SHORT).show()
                        }
                    })
                } catch (e: Exception) {
                    Toast.makeText(this@AfegirDespesa, "Error en carregar les dades: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }*/
        }
    }
}