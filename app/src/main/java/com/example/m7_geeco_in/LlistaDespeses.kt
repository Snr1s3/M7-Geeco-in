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
        val imports = arrayOf(200,300,400,500,600)
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val bundle = intent.extras
        if (bundle != null && bundle.size() != 0) {
            val stringValue = bundle?.getString("fNom") ?: "Null"
            val stringValue2 = bundle?.getString("fDiners") ?: "Null"
            AddData(noms, stringValue, imports, stringValue2)
        } else {
            AddData(noms, "Null", imports, "Null")
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

    fun AddData(noms: Array<String>, key: String, import: Array<Int>, key2: String){
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsView>()
        for (i in noms.indices) {
            if(key == "Null" && key2 == "Null"){
                val num =import[i]
                val imp = "$num€"
                data.add(ItemsView(R.drawable.money, noms[i], imp))
            }
            else if(key == "Null" && key2 != "Null") {
                if(import[i] == key2.toInt()){
                    val num =import[i]
                    val imp = "$num€"
                    data.add(ItemsView(R.drawable.money, noms[i], imp))
                }
            }
            else if(key != "Null" && key2 == "Null") {
                if(noms[i].contains(key)){
                    val num =import[i]
                    val imp = "$num€"
                    data.add(ItemsView(R.drawable.money, noms[i], imp))
                }
            }
            else{
                if(noms[i].contains(key)){
                    if(import[i] == key2.toInt()){
                        val num =import[i]
                        val imp = "$num€"
                        data.add(ItemsView(R.drawable.money, noms[i], imp))
                    }
                }
            }
        }
        val adapter = CustomAdapter(this, data)
        recyclerview.adapter = adapter
    }
}