package com.example.m7_geeco_in

import android.os.Bundle
import android.content.Intent;
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
// import com.example.recyclerview.reserves.ReservesAPI

data class Ingressos(val income_id:Int, val idUsuari:Int, val noms:String, val imports:Int)

class LlistaIngressos : AppCompatActivity() {
    companion object {
        val Ingressos:List<Ingressos> = listOf(
            Ingressos(1, 2, "Alba", 1000),
            Ingressos(2, 2, "Dani", 2000),
            Ingressos(3, 4, "Karolayn", 3000))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_ingressos)
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val bundle = intent.extras
       if (bundle != null && bundle.size() != 0) {
            val stringValue = bundle?.getString("fNom") ?: "Null"
            val stringValue2 = bundle.getString("fDiners") ?: "Null"
            val import = stringValue2.toIntOrNull() ?: 0
            AddData(Ingressos,"Null", "Null")
        } else {
            AddData(Ingressos, "Null", "Null")
        }


        b1.setOnClickListener{
            val intent = Intent(this@LlistaIngressos, Filtres::class.java)
            startActivity(intent)
        }
        b2.setOnClickListener{
            val intent = Intent(this@LlistaIngressos, AfegirIngres::class.java)
            startActivity(intent)
        }
    }

    fun AddData(Ingressos:List<Ingressos>, key: String, key2: String){
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsView>()
        for (ingres in LlistaIngressos.Ingressos) {
            val importsString = ingres.imports.toString()
            val importsString2 = "$importsString€"
            data.add(ItemsView(R.drawable.money, ingres.noms, importsString2))
        }



        val adapter = CustomAdapter(this, data)
        recyclerview.adapter = adapter
    }

}