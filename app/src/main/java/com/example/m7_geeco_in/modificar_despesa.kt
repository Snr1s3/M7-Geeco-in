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
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class modificar_despesa : AppCompatActivity() {
    private lateinit var despesa: Despesses
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar_despesa)
        val b1 = findViewById<Button>(R.id.b1)
        val itemId = intent.getIntExtra("ITEM_ID", -1)
        if (itemId != -1) {
            Toast.makeText(this@modificar_despesa, "Data loaded successfully DEspesa!", Toast.LENGTH_SHORT).show()

            fetchDespesa(itemId)
        }
        else{
            fetchDespesa(-1)
        }
        b1.setOnClickListener{
            val intent = Intent(this@modificar_despesa, LlistaDespeses::class.java)
            startActivity(intent)
        }
    }
    private fun fetchDespesa(id: Int) {
        Toast.makeText(this@modificar_despesa, id.toString(), Toast.LENGTH_SHORT).show()

        if (id >= 0) {
            lifecycleScope.launch {
                try {
                    despesa = geecoinAPI.API().despesaId(id)
                    if (despesa != null) {
                        Log.d("ModificarDespesa", "Despesa loaded: $despesa")
                        withContext(Dispatchers.Main) {
                            addData(despesa) // Add data to the UI
                            Toast.makeText(this@modificar_despesa, "Data loaded successfully!", Toast.LENGTH_SHORT).show()
                        }
                    } else {

                        Log.e("ModificarDespesa", "Error: despesa is null!")
                    }
                } catch (e: Exception) {
                    Log.e("ModificarDespesa", "Error fetching data: ${e.message}")
                }
            }
        }
    }



    private fun addData(despesa: Despesses) {
        Toast.makeText(this@modificar_despesa, "hola", Toast.LENGTH_SHORT).show()

        val t = findViewById<EditText>(R.id.et_titol)
        val descr = findViewById<EditText>(R.id.et_descripcio)
        val q = findViewById<EditText>(R.id.et_cuantitat)
        val data = findViewById<EditText>(R.id.et_data)

        t.setText(despesa.title ?: "No Title")
        if(despesa.desc == null ){
            descr.setText(null)
        }
        else{
            descr.setText(despesa.desc)
        }
        descr.setText(despesa.desc ?: "No Description")
        q.setText(String.format(Locale.getDefault(), "%,d", despesa.amount))
        data.setText(despesa.date ?: "No Date") // Handle null safely

        Log.d("ModificarIngres", "Title: ${despesa.title}, Desc: ${despesa.desc}, Amount: ${despesa.amount}, Date: ${despesa.date}")
    }
}