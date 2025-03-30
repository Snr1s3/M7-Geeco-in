package com.example.m7_geeco_in.ingres

import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m7_geeco_in.data.geecoinAPI
import retrofit2.HttpException // Keep this one
import androidx.lifecycle.lifecycleScope
import com.example.m7_geeco_in.R
import com.example.m7_geeco_in.fragments.Loading
import com.example.m7_geeco_in.models.Ingressos
import com.example.m7_geeco_in.recycler.CustomAdapter
import com.example.m7_geeco_in.recycler.ItemsView
import com.example.m7_geeco_in.screen.MenuAndroid
import kotlinx.coroutines.launch
import java.io.IOException

// import com.example.recyclerview.reserves.ReservesAPI



class LlistaIngressos : AppCompatActivity() {
    private  var incomes: List<Ingressos>? = listOf()
    private val loadingFragment = Loading()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MenuAndroid(window).hideSystemBar()
        setContentView(R.layout.activity_llista_ingressos)
        val b1 = findViewById<Button>(R.id.button1)
        val b2 = findViewById<Button>(R.id.button2)
        supportFragmentManager.beginTransaction()
            .replace(R.id.loading_container, loadingFragment)
            .commit()
        val bundle = intent.extras
        if (bundle != null && bundle.size() > 0) {
            val stringValue = bundle.getString("fNom")
            val stringValue2 = bundle.getString("fDiners")
            val stringValue3 = bundle.getString("fData")
            //Toast.makeText(this, "fNom: $stringValue, fDiners: $stringValue2, fData: $stringValue3", Toast.LENGTH_SHORT).show()
            val import = stringValue?.toIntOrNull()
            fetchIngressosList(0, 30, stringValue2, import, stringValue3)
        } else {
            //Toast.makeText(this, "Sense Filtres", Toast.LENGTH_SHORT).show()
            fetchIngressosList(0, 30, null, null, null)
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

    fun AddData(ingressos: List<Ingressos>, key: String?, key2: Int?, key3: String?) {
        val recyclerview = findViewById<RecyclerView>(R.id.recycler1)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsView>()
        for (ingres in ingressos) {
            val importsString2 = "${ingres.amount}€"
            val matchTitle = key == null || ingres.title == key
            val matchAmount = key2 == null || ingres.amount == key2
            val matchDate = key3 == null || ingres.date == key3

            if (matchTitle && matchAmount && matchDate) {
                data.add(ItemsView(R.drawable.money, ingres.id, ingres.title, importsString2))
            }
        }
        val adapter = CustomAdapter(this, data)
        recyclerview.adapter = adapter
    }

    private fun fetchIngressosList(skip: Int, limit: Int, key: String?, key2: Int?, key3: String?) {
        lifecycleScope.launch {
            try {
                incomes = geecoinAPI.API().getIngressosList(skip = skip, limit = limit)
                supportFragmentManager.beginTransaction()
                    .remove(loadingFragment)
                    .commit()
                findViewById<FrameLayout>(R.id.loading_container).visibility = View.GONE
                findViewById<RecyclerView>(R.id.recycler1).visibility= View.VISIBLE
                if (incomes != null) {
                    AddData(incomes!!, key, key2, key3)
                    Toast.makeText(this@LlistaIngressos, "Dades Carregades", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@LlistaIngressos, "No hi han dades disponibles", Toast.LENGTH_SHORT).show()
                }
            } catch (e: HttpException) {
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