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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m7_geeco_in.data.geecoinAPI
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

data class Despesses(val title:String, val desc:String, val amount:Int, val date:String, val id:Int)

class LlistaDespeses : AppCompatActivity() {

    private  var expenses: List<Despesses>? = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_llista_despeses)
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        val bundle = intent.extras
        if (bundle != null && bundle.size() != 0) {
            val stringValue = bundle.getString("fNom") ?: null
            val stringValue2 = bundle.getString("fDiners") ?: null
            val import = stringValue2?.toIntOrNull() ?: null
            fetchDespesesList(skip = 0, limit = 10,stringValue, import)
        } else {
            fetchDespesesList(skip = 0, limit = 10,null,null)
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

    fun AddData(Despesses:List<Despesses>, key: String?, key2: Int?){
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = ArrayList<ItemsView>()
        for (despesa in Despesses) {
            val importsString = despesa.amount.toString()
            val importsString2 = "$importsString€"
            if(key == null && key2 == null){
                data.add(ItemsView(R.drawable.money, despesa.title, importsString2))
            }
            if(key2 != null && key == null){
                if(despesa.amount == key2){
                    data.add(ItemsView(R.drawable.money, despesa.title, importsString2))
                }
            }
            if(key != null && key2 == null){
                if(despesa.title == key){
                    data.add(ItemsView(R.drawable.money, despesa.title, importsString2))
                }
            }
            else{
                if(key == despesa.title && key2 == despesa.amount ){
                    data.add(ItemsView(R.drawable.money, despesa.title, importsString2))
                }
            }
        }
        val adapter = CustomAdapter(this, data)
        recyclerview.adapter = adapter
    }

    private fun fetchDespesesList(skip: Int, limit: Int, key: String?, key2: Int?) {
        lifecycleScope.launch {
            try {
                expenses = geecoinAPI.API().getDespesesList(skip = skip, limit = limit)
                AddData(expenses!!, key, key2)
                Toast.makeText(this@LlistaDespeses, "Data loaded successfully!", Toast.LENGTH_SHORT).show()
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