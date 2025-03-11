package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.m7_geeco_in.data.DespesaRequest
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class modificar_despesa : AppCompatActivity() {

    private lateinit var despesa: Despesses
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_despesa)
        enableEdgeToEdge()
        val b1 = findViewById<Button>(R.id.b1)
        val deleteButton = findViewById<Button>(R.id.b2)
        val itemId = intent.getIntExtra("ITEM_ID", -1)
        if (itemId != -1) {
            fetchDespesa(itemId)
            deleteButton.setOnClickListener {
                if (itemId != -1) {
                    deleteDespesa(itemId)
                } else {
                    Toast.makeText(this, "ID de ingres no valid", Toast.LENGTH_SHORT).show()
                }
            }
        }
        b1.setOnClickListener {
            if (itemId != -1) {
                updateDespesa(itemId)
            } else {
                Toast.makeText(this, "ID de despesa no vàlid", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this@modificar_despesa, LlistaDespeses::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDespesa(despesaId: Int) {
        val api = geecoinAPI.API()

        lifecycleScope.launch {
            try {
                val response = api.deleteExpese(despesaId)
                if (response.isSuccessful) {
                    Toast.makeText(this@modificar_despesa, "Ingres eliminat amb exit", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@modificar_despesa, LlistaDespeses::class.java))
                    finish()
                } else {
                    Toast.makeText(this@modificar_despesa, "Error al eliminar el despesa", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@modificar_despesa, "Error de red: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchDespesa(id: Int) {
        if (id >= 0) {
            lifecycleScope.launch {
                try {
                    despesa = geecoinAPI.API().despesesId(id)
                    if (despesa != null) {
                        Log.d("ModificarIngres", "Income loaded: $despesa")
                        withContext(Dispatchers.Main) {
                            addData(despesa)
                            Toast.makeText(this@modificar_despesa, "Data loaded successfully!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e("ModificarIngres", "Error: income is null!")
                    }
                } catch (e: Exception) {
                    Log.e("ModificarIngres", "Error fetching data: ${e.message}")
                }
            }
        }
    }
    fun addData(despesa: Despesses) {
        val t = findViewById<EditText>(R.id.et_titol)
        val descr = findViewById<EditText>(R.id.et_descripion)
        val q = findViewById<EditText>(R.id.et_cuantitat)
        val data = findViewById<EditText>(R.id.et_data)
        t.setText(despesa.title)
        descr.setText(despesa.description)
        q.setText(String.format(Locale.getDefault(), "%,d", despesa.amount))
        data.setText(despesa.date)
        Log.d("ModificarDespesa", "Title: ${despesa.title}, Desc: ${despesa.description}, Amount: ${despesa.amount}, Date: ${despesa.date}")
    }
    fun updateDespesa(despesaId: Int) {
        val api = geecoinAPI.API()

        val title = findViewById<EditText>(R.id.et_titol).text.toString()
        val description = findViewById<EditText>(R.id.et_descripion).text.toString()
        val amount = findViewById<EditText>(R.id.et_cuantitat).text.toString().toIntOrNull() ?: 0
        val date = findViewById<EditText>(R.id.et_data).text.toString()

        val updatedDespesa = DespesaRequest(title, description, amount, date)

        lifecycleScope.launch {
            try {
                val response = api.updateDespesa(despesaId, updatedDespesa)
                if (response.isSuccessful) {
                    Toast.makeText(this@modificar_despesa, "Despesa actualitzada amb èxit", Toast.LENGTH_SHORT).show()
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error desconegut"
                    Toast.makeText(this@modificar_despesa, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
                    Log.e("ModificarDespesa", "Error: $errorMessage")
                }
            } catch (e: Exception) {
                Toast.makeText(this@modificar_despesa, "Error de xarxa: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}