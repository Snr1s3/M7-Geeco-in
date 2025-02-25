package com.example.m7_geeco_in

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Despesses (val expense_id:Int, val idUsuari:Int, val noms:String, val imports:Int)

class LlistaDespeses : AppCompatActivity() {

    companion object {
        val Despeses: List<Despesses> = listOf(
            Despesses(1, 1, "Alba", 200),
            Despesses(2,2,"Dani", 300),
            Despesses(3,3,"Karolayn", 400))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_despeses)
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val bundle = intent.extras
        if (bundle != null && bundle.size() != 0) {
            val stringValue = bundle?.getString("fNom") ?: "Null"
            val stringValue2 = bundle.getString("fDiners") ?: "Null"
            AddData(Despeses, "Null", "Null")
        } else {
            AddData(Despeses, "Null", "Null")
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

    fun AddData(Despesses:List<Despesses>, key: String, key2: String) {
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsView>()
        for (despesses in LlistaDespeses.Despeses) {
            val importsString = despesses.imports.toString()
            val importsString2 = "$importsString€"
            data.add(ItemsView(R.drawable.money, despesses.noms, importsString2))
        }

        val adapter = CustomAdapter(this, data)
        recyclerview.adapter = adapter
    }
}