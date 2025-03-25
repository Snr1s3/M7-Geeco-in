package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

data class Despesses(val title:String, val description:String, val amount:Int, val date:String, val id:Int)

class LlistaDespeses : AppCompatActivity() {

    private  var expenses: List<Despesses>? = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_despeses)
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val bundle = intent.extras
        if (bundle != null && bundle.size() > 0) {
            val stringValue = bundle.getString("fNom")
            val stringValue2 = bundle.getString("fDiners")
            val stringValue3 = bundle.getString("fData")
            val despesa = stringValue?.toIntOrNull()
            //Toast.makeText(this, "fNom: $stringValue, fDiners: $stringValue2, fData: $stringValue3", Toast.LENGTH_SHORT).show()
            fetchDespesesList(0, 30, stringValue2, despesa, stringValue3)
        } else {
            //Toast.makeText(this, "Sense Filtres", Toast.LENGTH_SHORT).show()
            fetchDespesesList(0, 30, null, null, null)
        }

        b1.setOnClickListener{
            val intent = Intent(this@LlistaDespeses, FiltresDespeses::class.java)
            startActivity(intent)
        }
        b2.setOnClickListener{
            val intent = Intent(this@LlistaDespeses, AfegirDespesa::class.java)
            startActivity(intent)
        }
    }

    fun AddData(Despesses:List<Despesses>, key: String?, key2: Int?,key3: String?){
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsView>()
        for (despesa in Despesses) {
            val importsString2 = "${despesa.amount}€"
            if (key == null && key2 == null && key3 == null) {
                data.add(ItemsView(R.drawable.money, despesa.id, despesa.title, importsString2))
            } else if (key == null && key2 != null && key3 == null && despesa.amount == key2) {
                data.add(ItemsView(R.drawable.money, despesa.id, despesa.title, importsString2))
            } else if (key != null && key2 == null && key3 == null && despesa.title == key) {
                data.add(ItemsView(R.drawable.money, despesa.id, despesa.title, importsString2))
            } else if (key == null && key2 == null && key3 != null && despesa.date == key3) {
                data.add(ItemsView(R.drawable.money, despesa.id, despesa.title, importsString2))
            } else if (key != null && key2 != null && key3 == null && key == despesa.title && despesa.amount == key2) {
                data.add(ItemsView(R.drawable.money, despesa.id, despesa.title, importsString2))
            } else if (key != null && key2 == null && key3 != null && key == despesa.title && key3 == despesa.date) {
                data.add(ItemsView(R.drawable.money, despesa.id, despesa.title, importsString2))
            } else if (key == null && key2 != null && key3 != null && key2 ==despesa.amount && despesa.date == key3) {
                data.add(ItemsView(R.drawable.money, despesa.id, despesa.title, importsString2))
            } else if (key == despesa.title && key2 == despesa.amount && key3 == despesa.date) {
                data.add(ItemsView(R.drawable.money, despesa.id, despesa.title, importsString2))
            }
        }
        val adapter = CustomAdapter(this, data)
        recyclerview.adapter = adapter
    }

    private fun fetchDespesesList(skip: Int, limit: Int, key: String?, key2: Int?,key3: String?){
        lifecycleScope.launch {
            try {
                expenses = geecoinAPI.API().getDespesesList(skip = skip, limit = limit)
                if (expenses != null) {
                    AddData(expenses!!, key, key2, key3)
                    Toast.makeText(this@LlistaDespeses, "Dades Carregades", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LlistaDespeses, "No hi han dades disponibles", Toast.LENGTH_SHORT).show()
                }
            }  catch (e: HttpException) {
                println("HTTP Error: ${e.message}")
            } catch (e: IOException) {
                println("Network Error: ${e.message}")
            } catch (e: Exception) {
                Toast.makeText(this@LlistaDespeses,"Error loading data", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@LlistaDespeses, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}