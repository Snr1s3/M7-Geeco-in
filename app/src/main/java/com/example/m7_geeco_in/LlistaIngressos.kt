package com.example.m7_geeco_in

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m7_geeco_in.data.geecoinAPI

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// import com.example.recyclerview.reserves.ReservesAPI

data class Ingressos(val title:String, val desc:String, val amount:Int, val date:String, val id:Int)

class LlistaIngressos : AppCompatActivity() {
    private  var incomes: List<Ingressos>? = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_ingressos)
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val bundle = intent.extras
       if (bundle != null && bundle.size() != 0) {
           val stringValue = bundle.getString("fNom") ?: null
           val stringValue2 = bundle.getString("fDiners") ?: null
           val stringValue3 = bundle.getString("fData") ?: null
           val import = stringValue2?.toIntOrNull() ?: null
           fetchIngressosList(skip = 0, limit = 10,stringValue, import, stringValue3)
        } else {
           fetchIngressosList(skip = 0, limit = 10,null, null, null)
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

    fun AddData(ingressos:List<Ingressos>, key: String?, key2: Int?,key3: String?){
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsView>()
        for (ingres in ingressos) {
            val importsString = ingres.amount.toString()
            val importsString2 = "$importsString€"

            if(key == null && key2 == null && key3 == null){
                data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
            }
            if(key == null && key2 != null && key3 == null){
                if(ingres.amount == key2){
                    data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
                }
            }
            if(key != null && key2 == null && key3 == null){
                if(ingres.title == key){
                    data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
                }
            }
            if(key == null && key2 == null && key3 != null){
                if(ingres.date == key3){
                    data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
                }
            }
            if(key != null && key2 != null && key3 == null){
                if(key == ingres.title && ingres.amount == key2){
                    data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
                }
            }
            if(key != null && key2 == null && key3 != null){
                if(key == ingres.title && key3 == ingres.date){
                    data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
                }
            }
            if(key == null && key2 != null && key3 != null){
                if(key2 == ingres.amount && ingres.date == key3){
                    data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
                }
            }
            else{
                if(key == ingres.title && key2 == ingres.amount && key3 == ingres.date){
                    data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
                }
            }
        }

        val adapter = CustomAdapter(this, data)
        recyclerview.adapter = adapter
    }
    private fun fetchIngressosList(skip: Int, limit: Int, key: String?, key2: Int?,key3: String?) {
        lifecycleScope.launch {
            try {
                incomes = geecoinAPI.API().getIngressosList(skip = skip, limit = limit)
                AddData(incomes!!, key, key2,key3)
                Toast.makeText(this@LlistaIngressos, "Data loaded successfully!", Toast.LENGTH_SHORT).show()
            }  catch (e: HttpException) {
                println("HTTP Error: ${e.message}")
            } catch (e: IOException) {
                println("Network Error: ${e.message}")
            } catch (e: Exception) {
                Toast.makeText(this@LlistaIngressos, "Error loading data", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@LlistaIngressos, "Error: ${e.message}", Toast.LENGTH_SHORT).show()

            }
        }
    }

}