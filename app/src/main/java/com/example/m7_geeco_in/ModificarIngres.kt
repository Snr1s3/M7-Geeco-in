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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.Locale

class ModificarIngres : AppCompatActivity() {
    private lateinit var income: Ingressos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_modificar_ingres)
        val b1 = findViewById<Button>(R.id.b1)
        val itemId = intent.getIntExtra("ITEM_ID", -1)
        if (itemId != -1) {
            fetchIngres(itemId)
        }
        else{
            fetchIngres(-1)
        }
        b1.setOnClickListener {
            val intent = Intent(this@ModificarIngres, LlistaIngressos::class.java)
            startActivity(intent)
        }
    }

    private fun fetchIngres(id: Int) {
        if(id>=0){
            lifecycleScope.launch {
                try {
                    income = geecoinAPI.API().ingresId(id)
                    if (income != null) {
                        Log.d("ModificarIngres", "Income loaded: $income")
                        withContext(Dispatchers.Main) {
                            addData(income)
                            Toast.makeText(this@ModificarIngres, "Data loaded successfully!", Toast.LENGTH_SHORT).show()
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
        val descr = findViewById<EditText>(R.id.et_descripcio)
        val q = findViewById<EditText>(R.id.et_cuantitat)
        val data = findViewById<EditText>(R.id.et_data)

        t.setText(income.title ?: "No Title")
        if(income.desc == null ){
            descr.setText(null)
        }
        else{
            descr.setText(income.desc)
        }
       descr.setText(income.desc ?: "No Description")
        q.setText(String.format(Locale.getDefault(), "%,d", income.amount))
        data.setText(income.date ?: "No Date") // Handle null safely

        Log.d("ModificarIngres", "Title: ${income.title}, Desc: ${income.desc}, Amount: ${income.amount}, Date: ${income.date}")
    }

}