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

class LlistaDespeses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_despeses)
        val noms = arrayOf("Alba", "Dani", "Karolayn", "David", "M07")
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val bundle = intent.extras
        if (bundle != null && bundle.size() != 0) {
            val stringValue = bundle?.getString("fNom") ?: "Null"
            AddData(noms, stringValue)
        } else {
            AddData(noms, "Null")
        }

        b1.setOnClickListener{
            val intent = Intent(this@LlistaDespeses, Filtres::class.java)
            startActivity(intent)
        }
        b2.setOnClickListener{
            val intent = Intent(this@LlistaDespeses, AfegirIngres::class.java)
            startActivity(intent)
        }
    }

    fun AddData(noms: Array<String>, key: String){
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsView>()
        for (nom in noms) {
            if(key == "Null"){
                data.add(ItemsView(R.drawable.money, nom))
            }
            else{
                if(nom.contains(key)){
                    data.add(ItemsView(R.drawable.money, nom))
                }
            }
        }
        val adapter = CustomAdapter(this, data)
        recyclerview.adapter = adapter
    }
}