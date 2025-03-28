package com.example.m7_geeco_in.ingres

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.data.geecoinAPI
import com.example.m7_geeco_in.models.IngresRequest
import com.example.m7_geeco_in.models.Ingressos
import com.example.m7_geeco_in.screen.MenuAndroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class ModificarIngres : AppCompatActivity() {
    private lateinit var income: Ingressos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MenuAndroid(window).hideSystemBar()
        setContentView(R.layout.activity_modificar_ingres)
        val b1 = findViewById<Button>(R.id.b1)
        val deleteButton = findViewById<Button>(R.id.b2)
        val itemId = intent.getIntExtra("ITEM_ID", -1)
        if (itemId != -1) {
            fetchIngres(itemId)
            deleteButton.setOnClickListener {
                if (itemId != -1) {
                    deleteIncome(itemId)
                } else {
                    Toast.makeText(this, "ID de ingres no valid", Toast.LENGTH_SHORT).show()
                }
            }
        }
        b1.setOnClickListener {
            if (itemId != -1) {
                updateIngres(itemId)
            } else {
                Toast.makeText(this, "ID del ingrés no vàlid", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this@ModificarIngres, LlistaIngressos::class.java)
            startActivity(intent)
        }
    }

    private fun deleteIncome(itemId: Int) {
        val api = geecoinAPI.API()

        lifecycleScope.launch {
            try {
                val response = api.deleteIngres(itemId)
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@ModificarIngres,
                        "Ingres eliminat amb exit",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@ModificarIngres, LlistaIngressos::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this@ModificarIngres,
                        "Error al eliminar el ingres",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@ModificarIngres,
                    "Error de red: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun fetchIngres(id: Int) {
        if (id >= 0) {
            lifecycleScope.launch {
                try {
                    income = geecoinAPI.API().ingresId(id)
                    if (income != null) {
                        Log.d("ModificarIngres", "Income loaded: $income")
                        withContext(Dispatchers.Main) {
                            addData(income)
                            Toast.makeText(
                                this@ModificarIngres,
                                "Data loaded successfully!",
                                Toast.LENGTH_SHORT
                            ).show()
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

    fun addData(income: Ingressos) {
        val t = findViewById<EditText>(R.id.et_titol)
        val descr = findViewById<EditText>(R.id.et_descripion)
        val q = findViewById<EditText>(R.id.et_cuantitat)
        val data = findViewById<EditText>(R.id.et_data)
        t.setText(income.title)
        descr.setText(income.description)
        q.setText(String.format(Locale.getDefault(), "%,d", income.amount))
        data.setText(income.date)
        Log.d(
            "ModificarIngres",
            "Title: ${income.title}, Desc: ${income.description}, Amount: ${income.amount}, Date: ${income.date}"
        )
    }

    fun updateIngres(ingresId: Int) {
        val api = geecoinAPI.API()

        val title = findViewById<EditText>(R.id.et_titol).text.toString()
        val description = findViewById<EditText>(R.id.et_descripion).text.toString()
        val amount = findViewById<EditText>(R.id.et_cuantitat).text.toString().toIntOrNull() ?: 0
        val date = findViewById<EditText>(R.id.et_data).text.toString()

        val updatedIngres = IngresRequest(title, description, amount, date)

        lifecycleScope.launch {

            try {
                val response = api.updateIngres(ingresId, updatedIngres)
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@ModificarIngres,
                        "Ingrés actualitzat amb èxit",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val errorMessage = response.errorBody()?.string() ?: "Error desconegut"
                    Toast.makeText(
                        this@ModificarIngres,
                        "Error: $errorMessage",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("ModificarIngrés", "Error: $errorMessage")
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@ModificarIngres,
                    "Error de xarxa: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}