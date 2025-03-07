package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.launch

class modificar_despesa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val b1 = findViewById<Button>(R.id.b1)
        val deleteButton = findViewById<Button>(R.id.b2)

        b1.setOnClickListener {
            val intent = Intent(this@modificar_despesa, LlistaIngressos::class.java)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {
            val despesaId = intent.getIntExtra("despesa_id", -1)
            if (despesaId != -1) {
                deleteDespesa(despesaId)
            } else {
                Toast.makeText(this, "ID de ingres no valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteDespesa(despesaId: Int) {
        val api = geecoinAPI.API()

        lifecycleScope.launch {
            try {
                val response = api.deleteExpese(despesaId)
                if (response.isSuccessful) {
                    Toast.makeText(this@modificar_despesa, "Ingres eliminat amb exit", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@modificar_despesa, LlistaIngressos::class.java))
                    finish()
                } else {
                    Toast.makeText(this@modificar_despesa, "Error al eliminar el despesa", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@modificar_despesa, "Error de red: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}