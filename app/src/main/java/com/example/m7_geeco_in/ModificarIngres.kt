package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.launch

class ModificarIngres : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar_ingres)
        val b1 = findViewById<Button>(R.id.b1)
        val deleteButton = findViewById<Button>(R.id.b2)

        b1.setOnClickListener {
            val intent = Intent(this@ModificarIngres, LlistaIngressos::class.java)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {
            val incomeId = intent.getIntExtra("income_id", -1)
            if (incomeId != -1) {
                deleteIncome(incomeId)
            } else {
                Toast.makeText(this, "ID de ingres no valid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteIncome(incomeId: Int) {
        val api = geecoinAPI.API()

        lifecycleScope.launch {
            try {
                val response = api.deleteIngres(incomeId)
                if (response.isSuccessful) {
                    Toast.makeText(this@ModificarIngres, "Ingres eliminat amb exit", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@ModificarIngres, LlistaIngressos::class.java))
                    finish()
                } else {
                    Toast.makeText(this@ModificarIngres, "Error al eliminar el ingres", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ModificarIngres, "Error de red: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}